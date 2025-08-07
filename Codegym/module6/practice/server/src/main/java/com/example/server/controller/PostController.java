package com.example.server.controller;

import com.example.server.dto.ContentGenerationRequestDTO;
import com.example.server.dto.PostResponseDTO;
import com.example.server.dto.SchedulePostRequestDTO;
import com.example.server.service.PostService;
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
@RequestMapping("/api/posts")
@Slf4j
@CrossOrigin(origins = "*")
@Tag(name = "Post Management", description = "APIs for generating content and scheduling posts (Tasks 10 & 11)")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
        log.info("PostController initialized successfully at {}", LocalDateTime.now());
    }

    @Operation(summary = "Generate post content using AI", description = """
            Generate multiple post contents for a topic using OpenAI GPT. 
            This is the main endpoint for **Task 10**.
            
            **Process:**
            1. Select an approved topic
            2. Configure tone, content type, and number of posts
            3. AI generates engaging content with optional images
            4. Review and edit content before scheduling
            
            **Note:** Topic must be in APPROVED status to generate content.
            """)
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Content generated successfully"), @ApiResponse(responseCode = "400", description = "Invalid request parameters or topic not approved"), @ApiResponse(responseCode = "404", description = "Topic not found"), @ApiResponse(responseCode = "500", description = "Internal server error or AI service unavailable")})
    @PostMapping("/generate")
    public CompletableFuture<ResponseEntity<List<PostResponseDTO>>> generateContent(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Content generation request parameters", required = true, content = @Content(mediaType = "application/json", schema = @Schema(implementation = ContentGenerationRequestDTO.class), examples = {@ExampleObject(name = "Social Media Posts", description = "Generate professional social media posts", value = """
            {
                "topicId": 1,
                "numberOfPosts": 3,
                "tone": "professional",
                "contentType": "social_post",
                "includeImage": true,
                "additionalInstructions": "Make it engaging with call-to-action and relevant hashtags"
            }
            """), @ExampleObject(name = "Blog Articles", description = "Generate detailed blog articles", value = """
            {
                "topicId": 1,
                "numberOfPosts": 2,
                "tone": "informative",
                "contentType": "article",
                "includeImage": false,
                "additionalInstructions": "Include technical details, examples, and actionable insights"
            }
            """), @ExampleObject(name = "Marketing Campaign", description = "Generate promotional content", value = """
            {
                "topicId": 1,
                "numberOfPosts": 5,
                "tone": "enthusiastic",
                "contentType": "promotion",
                "includeImage": true,
                "additionalInstructions": "Focus on benefits, create urgency, include clear CTA"
            }
            """)})) @Valid @RequestBody ContentGenerationRequestDTO request) {

        log.info("=== CONTENT GENERATION REQUEST ===");
        log.info("Topic ID: {}, Posts: {}, Tone: {}, Type: {}", request.getTopicId(), request.getNumberOfPosts(), request.getTone(), request.getContentType());
        log.info("Include Image: {}, Instructions: {}", request.getIncludeImage(), request.getAdditionalInstructions());

        return postService.generateContentForTopic(request).thenApply(posts -> {
            log.info("✅ Successfully generated {} posts for topic {}", posts.size(), request.getTopicId());
            return ResponseEntity.ok(posts);
        }).exceptionally(ex -> {
            log.error("❌ Error generating content for topic {}: {}", request.getTopicId(), ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        });
    }

    @Operation(summary = "Schedule a post for publishing", description = """
            Schedule a post to be published at a specific time on a social media channel. 
            This is the main endpoint for **Task 11**.
            
            **Scheduling Process:**
            1. Select a generated post (status must be DRAFT)
            2. Choose target social media channel
            3. Set publication date/time (must be in the future)
            4. Optionally modify final content
            5. System will auto-publish at scheduled time via background job
            
            **Time Format:** Use ISO 8601 format: YYYY-MM-DDTHH:MM:SS
            **Current UTC Time:** 2025-08-06 05:52:13
            """)
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Post scheduled successfully"), @ApiResponse(responseCode = "400", description = "Invalid request parameters, scheduled time in the past, or post not in DRAFT status"), @ApiResponse(responseCode = "404", description = "Post or social channel not found")})
    @PostMapping("/schedule")
    public ResponseEntity<PostResponseDTO> schedulePost(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Post scheduling request parameters", required = true, content = @Content(mediaType = "application/json", schema = @Schema(implementation = SchedulePostRequestDTO.class), examples = {@ExampleObject(name = "Schedule to Facebook", description = "Schedule post to Facebook page", value = """
            {
                "postId": 1,
                "socialChannelId": 1,
                "scheduledTime": "2025-08-07T10:30:00",
                "finalContent": "🚀 Exciting developments in AI technology! Our latest innovations are transforming how businesses operate. Stay tuned for more updates! #AI #Tech #Innovation"
            }
            """), @ExampleObject(name = "Schedule to LinkedIn", description = "Schedule professional post to LinkedIn", value = """
            {
                "postId": 2,
                "socialChannelId": 2,
                "scheduledTime": "2025-08-07T14:00:00",
                "finalContent": "Professional insights on the latest technology trends shaping our industry. Join the conversation about digital transformation and its impact on business growth."
            }
            """), @ExampleObject(name = "Schedule to Twitter", description = "Schedule tweet with current time + 2 hours", value = """
            {
                "postId": 3,
                "socialChannelId": 3,
                "scheduledTime": "2025-08-06T07:52:13",
                "finalContent": "Breaking: New tech trends emerging! 🔥 #TechNews #Innovation #Future"
            }
            """)})) @Valid @RequestBody SchedulePostRequestDTO request) {

        log.info("=== SCHEDULING POST ===");
        log.info("Post ID: {}, Channel: {}, Scheduled: {}", request.getPostId(), request.getSocialChannelId(), request.getScheduledTime());

        PostResponseDTO post = postService.schedulePost(request);
        log.info("✅ Post {} scheduled successfully for {}", post.getId(), request.getScheduledTime());
        return ResponseEntity.ok(post);
    }

    @Operation(summary = "Update post content", description = "Update the content of a post. User can modify AI-generated content before scheduling. Post status will be updated to indicate manual modification.")
    @PutMapping("/{postId}/content")
    public ResponseEntity<PostResponseDTO> updatePostContent(@Parameter(description = "Post ID to update", example = "1") @PathVariable Long postId, @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "New content for the post", required = true, content = @Content(mediaType = "text/plain", examples = @ExampleObject(name = "Updated Content", value = "This is the updated content for the post. User has modified the AI-generated content to better suit their needs and brand voice. #Updated #BrandVoice"))) @RequestBody String newContent, HttpServletRequest request) {

        Long userId = getCurrentUserId(request);
        log.info("User {} updating content for post {}", userId, postId);

        PostResponseDTO post = postService.updatePostContent(postId, newContent, userId);
        log.info("✅ Post {} content updated successfully", postId);
        return ResponseEntity.ok(post);
    }

    @Operation(summary = "Get posts by topic", description = "Retrieve all posts associated with a specific topic, ordered by creation date (newest first)")
    @GetMapping("/topic/{topicId}")
    public ResponseEntity<List<PostResponseDTO>> getPostsByTopic(@Parameter(description = "Topic ID to get posts for", example = "1") @PathVariable Long topicId) {

        log.info("Fetching posts for topic: {}", topicId);
        List<PostResponseDTO> posts = postService.getPostsByTopicId(topicId);
        log.info("✅ Found {} posts for topic {}", posts.size(), topicId);
        return ResponseEntity.ok(posts);
    }

    @Operation(summary = "Get post by ID", description = "Retrieve a specific post by its ID with full details including topic information")
    @GetMapping("/{postId}")
    public ResponseEntity<PostResponseDTO> getPostById(@Parameter(description = "Post ID", example = "1") @PathVariable Long postId) {

        log.info("Fetching post: {}", postId);
        PostResponseDTO post = postService.getPostById(postId);
        return ResponseEntity.ok(post);
    }

    @Operation(summary = "Get scheduled posts for current user", description = "Retrieve all scheduled posts for the current authenticated user")
    @GetMapping("/scheduled")
    public ResponseEntity<List<PostResponseDTO>> getScheduledPosts(HttpServletRequest request) {
        Long userId = getCurrentUserId(request);
        log.info("Fetching scheduled posts for user: {}", userId);

        List<PostResponseDTO> posts = postService.getScheduledPostsByUser(userId);
        log.info("✅ Found {} scheduled posts for user {}", posts.size(), userId);
        return ResponseEntity.ok(posts);
    }

    @Operation(summary = "Delete a post", description = "Permanently delete a post. Scheduled posts will be cancelled and removed from the publishing queue.")
    @ApiResponses(value = {@ApiResponse(responseCode = "204", description = "Post deleted successfully"), @ApiResponse(responseCode = "404", description = "Post not found")})
    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@Parameter(description = "Post ID to delete", example = "2") @PathVariable Long postId) {

        log.info("Deleting post: {}", postId);
        postService.deletePostById(postId);
        log.info("✅ Post {} deleted successfully", postId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Test endpoint", description = "Simple test endpoint to verify PostController is working")
    @GetMapping("/test")
    public ResponseEntity<Map<String, Object>> testEndpoint() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "SUCCESS");
        response.put("message", "PostController is working!");
        response.put("timestamp", LocalDateTime.now());
        response.put("service", postService != null ? "Available" : "NULL");
        return ResponseEntity.ok(response);
    }

    private Long getCurrentUserId(HttpServletRequest request) {
        // In production, extract from JWT token or session
        // For now, return mock user ID
        return 1L;
    }
}