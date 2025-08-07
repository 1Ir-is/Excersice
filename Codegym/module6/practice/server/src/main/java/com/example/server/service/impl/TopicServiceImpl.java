package com.example.server.service.impl;

import com.example.server.dto.TopicGenerationRequestDTO;
import com.example.server.dto.TopicResponseDTO;
import com.example.server.enums.TopicStatus;
import com.example.server.model.Campaign;
import com.example.server.model.Topic;
import com.example.server.repository.ICampaignRepository;
import com.example.server.repository.ITopicRepository;
import com.example.server.service.GPTService;
import com.example.server.service.TopicService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class TopicServiceImpl implements TopicService {

    private final ITopicRepository topicRepository;
    private final ICampaignRepository campaignRepository;
    private final GPTService gptService;
    private final ModelMapper modelMapper;
    private final ObjectMapper objectMapper;

    public TopicServiceImpl(ITopicRepository topicRepository, ICampaignRepository campaignRepository,
                            GPTService gptService, ModelMapper modelMapper, ObjectMapper objectMapper) {
        this.topicRepository = topicRepository;
        this.campaignRepository = campaignRepository;
        this.gptService = gptService;
        this.modelMapper = modelMapper;
        this.objectMapper = objectMapper;
    }

    @Override
    @Async
    public CompletableFuture<List<TopicResponseDTO>> generateTopicsForCampaign(TopicGenerationRequestDTO request) {
        try {
            Campaign campaign = campaignRepository.findById(request.getCampaignId())
                    .orElseThrow(() -> new EntityNotFoundException("Campaign not found"));

            String gptResponse = gptService.generateTopicsFromCampaign(
                    campaign,
                    request.getNumberOfTopics(),
                    request.getAdditionalInstructions()
            ).get();

            List<Topic> topics = parseTopicsFromGPTResponse(gptResponse, campaign.getId());
            List<Topic> savedTopics = topicRepository.saveAll(topics);

            // Manual mapping to avoid ModelMapper conflicts
            List<TopicResponseDTO> responseDTOs = savedTopics.stream()
                    .map(this::mapTopicToDTO)
                    .collect(Collectors.toList());

            log.info("Successfully completed topic generation - {} topics created", responseDTOs.size());
            return CompletableFuture.completedFuture(responseDTOs);

        } catch (Exception e) {
            log.error("Error in generateTopicsForCampaign: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to generate topics", e);
        }
    }

    @Override
    public TopicResponseDTO approveTopicById(Long topicId, Long userId) {
        Topic topic = topicRepository.findById(topicId)
                .orElseThrow(() -> new EntityNotFoundException("Topic not found"));

        topic.setStatus(TopicStatus.APPROVED);
        Topic savedTopic = topicRepository.save(topic);

        return mapTopicToDTO(savedTopic);
    }

    @Override
    public TopicResponseDTO rejectTopicById(Long topicId, Long userId) {
        log.info("rejecting topic {}", topicId);

        Topic topic = topicRepository.findById(topicId)
                .orElseThrow(() -> new EntityNotFoundException("Topic not found"));

        topic.setStatus(TopicStatus.REJECTED);
        Topic savedTopic = topicRepository.save(topic);

        return mapTopicToDTO(savedTopic);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TopicResponseDTO> getTopicsByCampaignId(Long campaignId) {
        List<Topic> topics = topicRepository.findByCampaignIdOrderByCreatedAtDesc(campaignId);
        return topics.stream()
                .map(this::mapTopicToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public TopicResponseDTO getTopicById(Long topicId) {
        Topic topic = topicRepository.findById(topicId)
                .orElseThrow(() -> new EntityNotFoundException("Topic not found"));
        return mapTopicToDTO(topic);
    }

    @Override
    public void deleteTopicById(Long topicId) {
        if (!topicRepository.existsById(topicId)) {
            throw new EntityNotFoundException("Topic not found");
        }
        topicRepository.deleteById(topicId);
        log.info("Topic {} deleted", topicId);
    }

    private TopicResponseDTO mapTopicToDTO(Topic topic) {
        TopicResponseDTO dto = new TopicResponseDTO();
        dto.setId(topic.getId());
        dto.setName(topic.getName());
        dto.setDescription(topic.getDescription());
        dto.setAiGenerated(topic.getAiGenerated());
        dto.setStatus(topic.getStatus());
        dto.setCreatedAt(topic.getCreatedAt());
        dto.setCampaignId(topic.getCampaignId()); // Direct field mapping - no conflicts
        return dto;
    }

    private List<Topic> parseTopicsFromGPTResponse(String gptResponse, Long campaignId) {
        try {
            log.info("Parsing GPT response for campaign {}", campaignId);

            JsonNode rootNode = objectMapper.readTree(gptResponse);
            JsonNode topicsNode = rootNode.get("topics");

            List<Topic> topics = new ArrayList<>();

            if (topicsNode != null && topicsNode.isArray()) {
                for (JsonNode topicNode : topicsNode) {
                    Topic topic = new Topic();
                    topic.setCampaignId(campaignId);
                    topic.setName(topicNode.get("name").asText());
                    topic.setDescription(topicNode.get("description").asText());
                    topic.setAiGenerated(true);
                    topic.setStatus(TopicStatus.PENDING);
                    topics.add(topic);
                }
            }

            log.info("Parsed {} topics from GPT response", topics.size());
            return topics;

        } catch (Exception e) {
            log.error("Error parsing topics from GPT response: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to parse GPT response", e);
        }
    }
}