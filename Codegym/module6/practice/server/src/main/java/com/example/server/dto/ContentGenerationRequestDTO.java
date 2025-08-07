package com.example.server.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Enhanced content generation request for user 1Ir-is - supports both short and long-form content")
public class ContentGenerationRequestDTO {

    @NotNull(message = "Topic ID is required")
    @Positive(message = "Topic ID must be positive")
    @Schema(description = "ID of the approved topic", example = "35")
    private Long topicId;

    @NotNull(message = "Number of posts is required")
    @Min(value = 1, message = "Must generate at least 1 post")
    @Max(value = 5, message = "Cannot generate more than 5 posts at once")
    @Schema(description = "Number of posts to generate", example = "1")
    private Integer numberOfPosts = 1;

    @Schema(description = "Tone of the content",
            example = "professional",
            allowableValues = {"professional", "casual", "enthusiastic", "informative", "promotional", "inspirational"})
    private String tone = "professional";

    @Schema(description = "Type and length of content to generate",
            example = "long_article",
            allowableValues = {
                    "social_post",      // 200-400 words
                    "article",          // 400-600 words
                    "long_article",     // 800-1200 words
                    "blog_post",        // 600-1000 words
                    "detailed_guide",   // 1000-1500 words
                    "white_paper",      // 1500-2000 words
                    "case_study",       // 800-1200 words
                    "story",           // 150-300 words
                    "email",           // 200-400 words
                    "promotion"        // 150-300 words
            })
    private String contentType = "social_post";

    @Min(value = 100, message = "Word count must be at least 100")
    @Max(value = 2000, message = "Word count cannot exceed 2000")
    @Schema(description = "Target word count for the content", example = "800")
    private Integer targetWordCount;

    @Schema(description = "Include structured sections", example = "true")
    private Boolean includeSections = false;

    @Schema(description = "Include introduction and conclusion", example = "true")
    private Boolean includeIntroConclusion = false;

    @Schema(description = "Include bullet points and lists", example = "true")
    private Boolean includeBulletPoints = false;

    @Schema(description = "Include call-to-action", example = "true")
    private Boolean includeCallToAction = true;

    @Schema(description = "Include statistics and data", example = "false")
    private Boolean includeStatistics = false;

    @Schema(description = "Include case studies or examples", example = "false")
    private Boolean includeCaseStudies = false;

    @Schema(description = "Whether to generate image prompts", example = "true")
    private Boolean includeImage = false;

    @Size(max = 500, message = "Additional instructions cannot exceed 500 characters")
    @Schema(description = "Additional instructions for content generation in Vietnamese",
            example = "Viết bài chi tiết về AI trong doanh nghiệp Việt Nam. Bao gồm ví dụ thực tế và hướng dẫn cụ thể.")
    private String additionalInstructions;

    @Schema(description = "Target platform for the content",
            example = "linkedin",
            allowableValues = {"facebook", "instagram", "linkedin", "twitter", "blog", "website", "email"})
    private String targetPlatform = "facebook";

    @Schema(description = "Target audience for the content",
            example = "business_owners",
            allowableValues = {"general", "business_owners", "young_professionals", "students", "tech_enthusiasts"})
    private String targetAudience = "general";
}