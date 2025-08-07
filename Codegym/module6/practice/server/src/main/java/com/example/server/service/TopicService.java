package com.example.server.service;

import com.example.server.dto.TopicGenerationRequestDTO;
import com.example.server.dto.TopicResponseDTO;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface TopicService {
    CompletableFuture<List<TopicResponseDTO>> generateTopicsForCampaign(TopicGenerationRequestDTO request);

    TopicResponseDTO approveTopicById(Long topicId, Long userId);

    TopicResponseDTO rejectTopicById(Long topicId, Long userId);

    List<TopicResponseDTO> getTopicsByCampaignId(Long campaignId);

    TopicResponseDTO getTopicById(Long topicId);

    void deleteTopicById(Long topicId);
}
