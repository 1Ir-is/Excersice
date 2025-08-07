package com.example.server.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContentGenerationRequestDTO {
    @NotNull(message = "Topic ID is required")
    private Long topicId;

    @Min(value = 1, message = "Number of posts must be at least 1")
    @Max(value = 10, message = "Number of posts must be at most 10")
    private Integer numberOfPosts = 3;

    // professional, casual, friendly
    private String tone = "professional";

    // article, announcement
    private String contentType = "social_post";

    private String additionalInstructions;

    private Boolean includeImage = false;
}