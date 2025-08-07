package com.example.server.controller;

import com.example.server.dto.TopicGenerationRequestDTO;
import com.example.server.dto.TopicResponseDTO;
import com.example.server.service.TopicService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/topics")
@Slf4j
@CrossOrigin(origins = "*")
@Tag(name = "Topic Management", description = "APIs for generating and managing marketing topics (Task 9)")
public class TopicController {

    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
        log.info("TopicController initialized successfully at {}", LocalDateTime.now());
    }

    @Operation(
            summary = "Generate marketing topics using AI",
            description = """
                    Generate multiple marketing topics for a campaign using OpenAI GPT.
                    This is the main endpoint for **Task 9**.

                    **How it works:**
                    1. Provide campaign ID and number of topics needed
                    2. AI analyzes campaign context and generates relevant topics
                    3. Topics are saved with PENDING status for user review
                    4. User can then approve/reject topics before using them for content generation

                    **Prerequisites:** Campaign must exist in the database
                    **Generated topics status:** PENDING (requires approval before content generation)
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Topics generated successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TopicResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request parameters (e.g., numberOfTopics out of range)",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Campaign not found",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error or AI service unavailable",
                    content = @Content(mediaType = "application/json")
            )
    })
    @PostMapping("/generate")
    public CompletableFuture<ResponseEntity<List<TopicResponseDTO>>> generateTopics(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Topic generation request parameters",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TopicGenerationRequestDTO.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Technology Company",
                                            description = "Generate topics for a tech company campaign",
                                            value = """
                                                    {
                                                        "campaignId": 1,
                                                        "numberOfTopics": 5,
                                                        "additionalInstructions": "Focus on technology and innovation themes for a software company. Include AI, cloud computing, and digital transformation topics."
                                                    }
                                                    """
                                    ),
                                    @ExampleObject(
                                            name = "E-commerce Business",
                                            description = "Generate topics for an e-commerce campaign",
                                            value = """
                                                    {
                                                        "campaignId": 1,
                                                        "numberOfTopics": 3,
                                                        "additionalInstructions": "Focus on online shopping, customer experience, seasonal promotions, and mobile commerce trends."
                                                    }
                                                    """
                                    ),
                                    @ExampleObject(
                                            name = "Healthcare Sector",
                                            description = "Generate topics for healthcare industry",
                                            value = """
                                                    {
                                                        "campaignId": 1,
                                                        "numberOfTopics": 4,
                                                        "additionalInstructions": "Focus on telemedicine, patient care innovation, health technology, and wellness trends."
                                                    }
                                                    """
                                    )
                            }
                    )
            )
            @Valid @RequestBody TopicGenerationRequestDTO request) {

        log.info("=== TOPIC GENERATION REQUEST ===");
        log.info("Campaign ID: {}, Number of topics: {}", request.getCampaignId(), request.getNumberOfTopics());
        log.info("Additional instructions: {}", request.getAdditionalInstructions());
        log.info("TopicService available: {}", topicService != null);

        return topicService.generateTopicsForCampaign(request)
                .thenApply(topics -> {
                    log.info("✅ Successfully generated {} topics for campaign {}", topics.size(), request.getCampaignId());
                    return ResponseEntity.ok(topics);
                })
                .exceptionally(ex -> {
                    log.error("❌ Error generating topics for campaign {}: {}", request.getCampaignId(), ex.getMessage(), ex);
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                });
    }

    @Operation(
            summary = "Get all topics for a campaign",
            description = "Retrieve all topics associated with a specific campaign ID, including their approval status. Results are ordered by creation date (newest first)."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Topics retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Campaign not found or no topics exist")
    })
    @GetMapping("/campaign/{campaignId}")
    public ResponseEntity<List<TopicResponseDTO>> getTopicsByCampaign(
            @Parameter(description = "Campaign ID to get topics for", example = "1")
            @PathVariable Long campaignId) {

        log.info("Fetching topics for campaign: {}", campaignId);
        List<TopicResponseDTO> topics = topicService.getTopicsByCampaignId(campaignId);
        log.info("✅ Found {} topics for campaign {}", topics.size(), campaignId);
        return ResponseEntity.ok(topics);
    }

    @Operation(
            summary = "Get topic by ID",
            description = "Retrieve a specific topic by its ID with full details including status and timestamps"
    )
    @GetMapping("/{topicId}")
    public ResponseEntity<TopicResponseDTO> getTopicById(
            @Parameter(description = "Topic ID", example = "1")
            @PathVariable Long topicId) {

        log.info("Fetching topic: {}", topicId);
        TopicResponseDTO topic = topicService.getTopicById(topicId);
        return ResponseEntity.ok(topic);
    }

    @Operation(
            summary = "Approve a topic",
            description = """
                    Approve a pending topic so it can be used for content generation.

                    **Status Change:** PENDING → APPROVED
                    **Effect:** Topic becomes available for content generation
                    **Note:** Only topics with PENDING status can be approved
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Topic approved successfully"),
            @ApiResponse(responseCode = "404", description = "Topic not found"),
            @ApiResponse(responseCode = "400", description = "Topic is not in pending status or already processed")
    })
    @PutMapping("/{topicId}/approve")
    public ResponseEntity<TopicResponseDTO> approveTopic(
            @Parameter(description = "Topic ID to approve", example = "1")
            @PathVariable Long topicId,
            HttpServletRequest request) {

        Long userId = getCurrentUserId(request);
        log.info("User {} approving topic {}", userId, topicId);

        TopicResponseDTO topic = topicService.approveTopicById(topicId, userId);
        log.info("✅ Topic {} approved successfully by user {}", topicId, userId);
        return ResponseEntity.ok(topic);
    }

    @Operation(
            summary = "Reject a topic",
            description = """
                    Reject a pending topic. Rejected topics cannot be used for content generation.

                    **Status Change:** PENDING → REJECTED
                    **Effect:** Topic becomes unavailable for content generation
                    **Note:** Only topics with PENDING status can be rejected
                    """
    )
    @PutMapping("/{topicId}/reject")
    public ResponseEntity<TopicResponseDTO> rejectTopic(
            @Parameter(description = "Topic ID to reject", example = "2")
            @PathVariable Long topicId,
            HttpServletRequest request) {

        Long userId = getCurrentUserId(request);
        log.info("User {} rejecting topic {}", userId, topicId);

        TopicResponseDTO topic = topicService.rejectTopicById(topicId, userId);
        log.info("✅ Topic {} rejected successfully by user {}", topicId, userId);
        return ResponseEntity.ok(topic);
    }

    @Operation(
            summary = "Delete a topic",
            description = """
                    Permanently delete a topic and all its associated posts. This action cannot be undone.

                    **Warning:** This will also delete all posts generated from this topic
                    **Cascade Effect:** All related posts will be removed
                    **Use Case:** Clean up unwanted topics
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Topic deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Topic not found")
    })
    @DeleteMapping("/{topicId}")
    public ResponseEntity<Void> deleteTopic(
            @Parameter(description = "Topic ID to delete", example = "3")
            @PathVariable Long topicId) {

        log.info("Deleting topic: {}", topicId);
        topicService.deleteTopicById(topicId);
        log.info("✅ Topic {} deleted successfully", topicId);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Test endpoint",
            description = "Simple test endpoint to verify TopicController is working"
    )
    @GetMapping("/test")
    public ResponseEntity<Map<String, Object>> testEndpoint() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "SUCCESS");
        response.put("message", "TopicController is working!");
        response.put("timestamp", LocalDateTime.now());
        response.put("service", topicService != null ? "Available" : "NULL");
        response.put("currentUser", getCurrentUserId(null));
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Health check endpoint",
            description = "Health check endpoint with detailed service information"
    )
    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> healthCheck() {
        Map<String, Object> health = new HashMap<>();
        health.put("status", "UP");
        health.put("timestamp", LocalDateTime.now());
        health.put("service", "TopicService");
        health.put("controller", "TopicController");
        health.put("version", "1.0.0");
        health.put("currentUtcTime", "2025-08-06 05:52:13");
        return ResponseEntity.ok(health);
    }

    private Long getCurrentUserId(HttpServletRequest request) {
        // In production, extract from JWT token or session
        // For now, return mock user ID based on current user login
        return 1L; // Can be mapped to actual user: 1Ir-is
    }
}