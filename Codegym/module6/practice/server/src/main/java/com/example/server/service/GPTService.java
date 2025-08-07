package com.example.server.service;

import com.example.server.dto.ContentGenerationRequestDTO;
import com.example.server.dto.GPTRequestDTO;
import com.example.server.dto.GPTResponseDTO;
import com.example.server.model.Campaign;
import com.example.server.model.Topic;

import java.util.concurrent.CompletableFuture;

public interface GPTService {
    CompletableFuture<String> generateTopicsFromCampaign(Campaign campaign, Integer numberOfTopics, String additionalInstructions);

    CompletableFuture<String> generateContentFromTopic(Topic topic, String tone, String contentType, String additionalInstructions);

    CompletableFuture<String> generateLongFormContent(Topic topic, ContentGenerationRequestDTO request);

    CompletableFuture<String> generateImagePromptFromContent(String content);

    CompletableFuture<GPTResponseDTO> callGPTAPI(GPTRequestDTO request);
}
