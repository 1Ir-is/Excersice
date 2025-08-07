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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/posts")
@Slf4j
@CrossOrigin(origins = "*")
@Tag(name = "Enhanced Content Generation", description = "APIs for generating Vietnamese content (short & long-form) and scheduling posts - User 1Ir-is")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
        log.info("🇻🇳 Enhanced PostController initialized successfully for user 1Ir-is at 2025-08-07 09:45:21");
    }

    @Operation(
            summary = "Generate Vietnamese content using AI (Enhanced)",
            description = """
                    Generate high-quality Vietnamese content from approved topics using OpenAI GPT.
                    **Enhanced for User 1Ir-is at 2025-08-07 09:45:21**
                    
                    **New Features:**
                    - ✅ Vietnamese language optimization
                    - ✅ Long-form content support (800-2000 words)
                    - ✅ Content structure options
                    - ✅ Target word count control
                    - ✅ Advanced content metrics
                    - ✅ Multiple content types
                    
                    **Content Types Supported:**
                    - Short-form: social_post, story, email (150-400 words)
                    - Medium-form: article, blog_post (400-800 words)
                    - Long-form: long_article, detailed_guide, white_paper, case_study (800-2000 words)
                    
                    **Process:**
                    1. Select an approved topic (status: APPROVED)
                    2. Configure content parameters (tone, type, word count)
                    3. AI generates structured Vietnamese content
                    4. Content includes title, body, hashtags, and metrics
                    5. Optional image prompt generation
                    6. Review and edit before scheduling
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vietnamese content generated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid parameters or topic not approved"),
            @ApiResponse(responseCode = "404", description = "Topic not found"),
            @ApiResponse(responseCode = "500", description = "AI service error")
    })
    @PostMapping("/generate")
    public CompletableFuture<ResponseEntity<List<PostResponseDTO>>> generateContent(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Enhanced content generation request for user 1Ir-is",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ContentGenerationRequestDTO.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Vietnamese Social Media Post",
                                            description = "Generate engaging Vietnamese social media content",
                                            value = """
                                                    {
                                                        "topicId": 35,
                                                        "numberOfPosts": 2,
                                                        "tone": "enthusiastic",
                                                        "contentType": "social_post",
                                                        "targetWordCount": 300,
                                                        "includeImage": true,
                                                        "includeBulletPoints": true,
                                                        "includeCallToAction": true,
                                                        "targetPlatform": "facebook",
                                                        "targetAudience": "business_owners",
                                                        "additionalInstructions": "Viết theo phong cách người Việt trẻ, năng động. Nhấn mạnh lợi ích thực tế cho doanh nghiệp Việt Nam."
                                                    }
                                                    """
                                    ),
                                    @ExampleObject(
                                            name = "Vietnamese Long-form Article",
                                            description = "Generate detailed Vietnamese business article",
                                            value = """
                                                    {
                                                        "topicId": 35,
                                                        "numberOfPosts": 1,
                                                        "tone": "professional",
                                                        "contentType": "long_article",
                                                        "targetWordCount": 1200,
                                                        "includeSections": true,
                                                        "includeIntroConclusion": true,
                                                        "includeBulletPoints": true,
                                                        "includeStatistics": true,
                                                        "includeCaseStudies": true,
                                                        "includeCallToAction": true,
                                                        "includeImage": true,
                                                        "targetPlatform": "linkedin",
                                                        "targetAudience": "executives",
                                                        "additionalInstructions": "Viết bài chi tiết về ứng dụng AI trong doanh nghiệp Việt Nam. Bao gồm case study thực tế, số liệu cụ thể về ROI, và hướng dẫn triển khai cho CEO và quản lý cấp cao."
                                                    }
                                                    """
                                    ),
                                    @ExampleObject(
                                            name = "Vietnamese Detailed Guide",
                                            description = "Generate comprehensive Vietnamese how-to guide",
                                            value = """
                                                    {
                                                        "topicId": 36,
                                                        "numberOfPosts": 1,
                                                        "tone": "informative",
                                                        "contentType": "detailed_guide",
                                                        "targetWordCount": 1500,
                                                        "includeSections": true,
                                                        "includeIntroConclusion": true,
                                                        "includeBulletPoints": true,
                                                        "includeCallToAction": true,
                                                        "targetPlatform": "blog",
                                                        "targetAudience": "tech_enthusiasts",
                                                        "additionalInstructions": "Tạo hướng dẫn từng bước chi tiết cho doanh nghiệp Việt Nam. Bao gồm checklist, tips thực tế và lưu ý quan trọng."
                                                    }
                                                    """
                                    ),
                                    @ExampleObject(
                                            name = "Vietnamese Case Study",
                                            description = "Generate business case study in Vietnamese",
                                            value = """
                                                    {
                                                        "topicId": 37,
                                                        "numberOfPosts": 1,
                                                        "tone": "professional",
                                                        "contentType": "case_study",
                                                        "targetWordCount": 1000,
                                                        "includeSections": true,
                                                        "includeIntroConclusion": true,
                                                        "includeStatistics": true,
                                                        "includeCaseStudies": true,
                                                        "includeCallToAction": true,
                                                        "targetPlatform": "website",
                                                        "targetAudience": "business_owners",
                                                        "additionalInstructions": "Viết case study về thành công của doanh nghiệp Việt Nam trong chuyển đổi số. Bao gồm challenges, solutions, và results cụ thể."
                                                    }
                                                    """
                                    )
                            }
                    )
            )
            @Valid @RequestBody ContentGenerationRequestDTO request) {

        log.info("=== ENHANCED CONTENT GENERATION REQUEST for USER 1Ir-is ===");
        log.info("Timestamp: 2025-08-07 09:45:21 UTC");
        log.info("Topic ID: {}, Posts: {}, Tone: {}, Type: {}",
                request.getTopicId(), request.getNumberOfPosts(), request.getTone(), request.getContentType());
        log.info("Target Word Count: {}, Platform: {}, Audience: {}",
                request.getTargetWordCount(), request.getTargetPlatform(), request.getTargetAudience());
        log.info("Include Image: {}, Sections: {}, Bullet Points: {}",
                request.getIncludeImage(), request.getIncludeSections(), request.getIncludeBulletPoints());
        log.info("Instructions: {}", request.getAdditionalInstructions());

        return postService.generateContentForTopic(request)
                .thenApply(posts -> {
                    log.info("✅ Successfully generated {} Vietnamese posts for topic {} for user 1Ir-is",
                            posts.size(), request.getTopicId());
                    return ResponseEntity.ok(posts);
                })
                .exceptionally(ex -> {
                    log.error("❌ Error generating Vietnamese content for topic {} for user 1Ir-is: {}",
                            request.getTopicId(), ex.getMessage(), ex);
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                });
    }

    @Operation(
            summary = "Schedule Vietnamese post for publishing",
            description = """
                    Schedule a Vietnamese post to be published at a specific time on social media.
                    **Enhanced for User 1Ir-is at 2025-08-07 09:45:21**
                    
                    **Scheduling Process:**
                    1. Select a generated post (status: DRAFT)
                    2. Choose target social media channel
                    3. Set publication date/time (must be future)
                    4. Optionally modify final Vietnamese content
                    5. System auto-publishes at scheduled time
                    
                    **Supported Platforms:**
                    - Facebook, Instagram, LinkedIn, Twitter, TikTok, YouTube
                    
                    **Time Format:** ISO 8601: YYYY-MM-DDTHH:MM:SS
                    **Current UTC Time:** 2025-08-07 09:45:21
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Post scheduled successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid parameters or past scheduled time"),
            @ApiResponse(responseCode = "404", description = "Post or social channel not found")
    })
    @PostMapping("/schedule")
    public ResponseEntity<PostResponseDTO> schedulePost(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Post scheduling request for user 1Ir-is",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SchedulePostRequestDTO.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Schedule Vietnamese Facebook Post",
                                            description = "Schedule Vietnamese content to Facebook",
                                            value = """
                                                    {
                                                        "postId": 48,
                                                        "socialChannelId": 1,
                                                        "scheduledTime": "2025-08-07T15:30:00",
                                                        "finalContent": "🚀 AI Thay Đổi Cách Làm Việc - Bạn Đã Sẵn Sàng?\\n\\nTrí tuệ nhân tạo không còn là tương lai xa vời mà đã trở thành hiện tại! 🤖\\n\\n✨ Lợi ích thực tế cho doanh nghiệp Việt Nam:\\n• Tăng hiệu suất lên 40%\\n• Giảm thời gian xử lý công việc\\n• Cải thiện trải nghiệm khách hàng\\n\\n👉 Doanh nghiệp của bạn đã sẵn sàng chuyển đổi chưa?\\n💬 Chia sẻ kinh nghiệm trong comments!\\n\\n#AI #ChuyenDoiSo #DoanhNghiepVietNam #CongNghe #TuongLai"
                                                    }
                                                    """
                                    ),
                                    @ExampleObject(
                                            name = "Schedule Vietnamese LinkedIn Article",
                                            description = "Schedule professional Vietnamese content to LinkedIn",
                                            value = """
                                                    {
                                                        "postId": 49,
                                                        "socialChannelId": 2,
                                                        "scheduledTime": "2025-08-08T09:00:00",
                                                        "finalContent": "Chuyển Đổi Số trong Doanh Nghiệp Việt Nam: Hành Trình Từ Thách Thức đến Thành Công\\n\\nTrong bối cảnh kinh tế số phát triển mạnh mẽ, các doanh nghiệp Việt Nam đang đối mặt với áp lực chuyển đổi số để duy trì khả năng cạnh tranh...\\n\\n[Long-form professional content continues]\\n\\n#ChuyenDoiSo #DoanhNghiepVietNam #QuanLy #Leadership"
                                                    }
                                                    """
                                    )
                            }
                    )
            )
            @Valid @RequestBody SchedulePostRequestDTO request) {

        log.info("=== SCHEDULING VIETNAMESE POST for USER 1Ir-is ===");
        log.info("Timestamp: 2025-08-07 09:45:21 UTC");
        log.info("Post ID: {}, Channel: {}, Scheduled: {}",
                request.getPostId(), request.getSocialChannelId(), request.getScheduledTime());

        PostResponseDTO post = postService.schedulePost(request);
        log.info("✅ Vietnamese post {} scheduled successfully for {} by user 1Ir-is",
                post.getId(), request.getScheduledTime());
        return ResponseEntity.ok(post);
    }

    @Operation(
            summary = "Update Vietnamese post content",
            description = "Update Vietnamese post content. User can modify AI-generated content before scheduling. Maintains Vietnamese language quality."
    )
    @PutMapping("/{postId}/content")
    public ResponseEntity<PostResponseDTO> updatePostContent(
            @Parameter(description = "Post ID to update", example = "48")
            @PathVariable Long postId,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "New Vietnamese content for the post",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Updated Vietnamese Content",
                                    value = """
                                            {
                                                "title": "🚀 AI Thay Đổi Cách Làm Việc - Cập Nhật Mới",
                                                "content": "Trí tuệ nhân tạo đang thực sự cách mạng hóa cách chúng ta làm việc. Tại Việt Nam, nhiều doanh nghiệp đã áp dụng thành công AI để tối ưu hóa quy trình và tăng hiệu suất.\\n\\n🎯 Lợi ích cụ thể:\\n• Tiết kiệm 30-40% thời gian xử lý\\n• Giảm sai sót trong công việc\\n• Tăng sự hài lòng của khách hàng\\n\\n💡 Bạn muốn tìm hiểu cách áp dụng AI cho doanh nghiệp? Hãy liên hệ với chúng tôi!\\n\\n#AI #ChuyenDoiSo #DoanhNghiepVietNam #Innovation #Vietnam"
                                            }
                                            """
                            )
                    )
            )
            @RequestBody Map<String, String> contentUpdate,
            HttpServletRequest request) {

        Long userId = getCurrentUserId(request);
        log.info("User 1Ir-is updating Vietnamese content for post {} at 2025-08-07 09:45:21", postId);

        String title = contentUpdate.get("title");
        String content = contentUpdate.get("content");
        String fullContent = (title != null ? title + "\n\n" : "") + (content != null ? content : "");

        PostResponseDTO post = postService.updatePostContent(postId, fullContent, userId);
        log.info("✅ Vietnamese post {} content updated successfully by user 1Ir-is", postId);
        return ResponseEntity.ok(post);
    }

    // ========== ENHANCED QUERY ENDPOINTS ==========

    @Operation(
            summary = "Get posts by content type",
            description = "Retrieve posts by content type (social_post, long_article, blog_post, etc.)"
    )
    @GetMapping("/content-type/{contentType}")
    public ResponseEntity<List<PostResponseDTO>> getPostsByContentType(
            @Parameter(description = "Content type", example = "long_article")
            @PathVariable String contentType) {

        log.info("Fetching posts by content type: {} for user 1Ir-is", contentType);
        List<PostResponseDTO> posts = postService.getPostsByContentType(contentType);
        log.info("✅ Found {} posts of type {} for user 1Ir-is", posts.size(), contentType);
        return ResponseEntity.ok(posts);
    }

    @Operation(
            summary = "Get posts by target platform",
            description = "Retrieve posts by target platform (facebook, linkedin, instagram, etc.)"
    )
    @GetMapping("/platform/{platform}")
    public ResponseEntity<List<PostResponseDTO>> getPostsByPlatform(
            @Parameter(description = "Target platform", example = "linkedin")
            @PathVariable String platform) {

        log.info("Fetching posts by platform: {} for user 1Ir-is", platform);
        List<PostResponseDTO> posts = postService.getPostsByTargetPlatform(platform);
        log.info("✅ Found {} posts for platform {} for user 1Ir-is", posts.size(), platform);
        return ResponseEntity.ok(posts);
    }

    @Operation(
            summary = "Get long-form posts",
            description = "Retrieve all long-form posts (word count > 500)"
    )
    @GetMapping("/long-form")
    public ResponseEntity<List<PostResponseDTO>> getLongFormPosts() {
        log.info("Fetching long-form posts for user 1Ir-is");
        List<PostResponseDTO> posts = postService.getLongFormPosts();
        log.info("✅ Found {} long-form posts for user 1Ir-is", posts.size());
        return ResponseEntity.ok(posts);
    }

    @Operation(
            summary = "Get high-engagement posts",
            description = "Retrieve posts with high engagement scores"
    )
    @GetMapping("/high-engagement")
    public ResponseEntity<List<PostResponseDTO>> getHighEngagementPosts(
            @Parameter(description = "Minimum engagement score", example = "8.0")
            @RequestParam(defaultValue = "8.0") Double minScore) {

        log.info("Fetching high-engagement posts (score >= {}) for user 1Ir-is", minScore);
        List<PostResponseDTO> posts = postService.getPostsByEngagementScore(minScore);
        log.info("✅ Found {} high-engagement posts for user 1Ir-is", posts.size());
        return ResponseEntity.ok(posts);
    }

    @Operation(
            summary = "Get recent posts by user",
            description = "Retrieve recent posts for current user"
    )
    @GetMapping("/recent")
    public ResponseEntity<List<PostResponseDTO>> getRecentPosts(
            @Parameter(description = "Number of posts to return", example = "10")
            @RequestParam(defaultValue = "10") int limit,
            HttpServletRequest request) {

        Long userId = getCurrentUserId(request);
        log.info("Fetching {} recent posts for user 1Ir-is", limit);
        List<PostResponseDTO> posts = postService.getRecentPostsByUser(userId, limit);
        log.info("✅ Found {} recent posts for user 1Ir-is", posts.size());
        return ResponseEntity.ok(posts);
    }

    // ========== EXISTING ENDPOINTS (Updated) ==========

    @Operation(summary = "Get posts by topic", description = "Retrieve all Vietnamese posts for a specific topic")
    @GetMapping("/topic/{topicId}")
    public ResponseEntity<List<PostResponseDTO>> getPostsByTopic(
            @Parameter(description = "Topic ID", example = "35")
            @PathVariable Long topicId) {

        log.info("Fetching Vietnamese posts for topic: {} for user 1Ir-is", topicId);
        List<PostResponseDTO> posts = postService.getPostsByTopicId(topicId);
        log.info("✅ Found {} Vietnamese posts for topic {} for user 1Ir-is", posts.size(), topicId);
        return ResponseEntity.ok(posts);
    }

    @Operation(summary = "Get post by ID", description = "Retrieve specific Vietnamese post with complete details")
    @GetMapping("/{postId}")
    public ResponseEntity<PostResponseDTO> getPostById(
            @Parameter(description = "Post ID", example = "48")
            @PathVariable Long postId) {

        log.info("Fetching Vietnamese post: {} for user 1Ir-is", postId);
        PostResponseDTO post = postService.getPostById(postId);
        return ResponseEntity.ok(post);
    }

    @Operation(summary = "Get scheduled posts", description = "Retrieve all scheduled Vietnamese posts")
    @GetMapping("/scheduled")
    public ResponseEntity<List<PostResponseDTO>> getScheduledPosts(HttpServletRequest request) {
        Long userId = getCurrentUserId(request);
        log.info("Fetching scheduled Vietnamese posts for user 1Ir-is");

        List<PostResponseDTO> posts = postService.getScheduledPostsByUser(userId);
        log.info("✅ Found {} scheduled Vietnamese posts for user 1Ir-is", posts.size());
        return ResponseEntity.ok(posts);
    }

    @Operation(summary = "Delete a post", description = "Permanently delete a Vietnamese post")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Post deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Post not found")
    })
    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(
            @Parameter(description = "Post ID to delete", example = "48")
            @PathVariable Long postId) {

        log.info("Deleting Vietnamese post: {} for user 1Ir-is", postId);
        postService.deletePostById(postId);
        log.info("✅ Vietnamese post {} deleted successfully by user 1Ir-is", postId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Enhanced test endpoint", description = "Test endpoint with enhanced features info")
    @GetMapping("/test")
    public ResponseEntity<Map<String, Object>> testEndpoint() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "SUCCESS");
        response.put("message", "Enhanced PostController for Vietnamese content generation is working!");
        response.put("user", "1Ir-is");
        response.put("timestamp", "2025-08-07 09:45:21 UTC");
        response.put("features", Map.of(
                "vietnamese_content", true,
                "long_form_support", true,
                "content_metrics", true,
                "enhanced_scheduling", true,
                "multiple_platforms", true
        ));
        response.put("service", postService != null ? "Available" : "NULL");
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Health check with metrics", description = "Enhanced health check with system metrics")
    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> healthCheck() {
        Map<String, Object> health = new HashMap<>();
        health.put("status", "UP");
        health.put("user", "1Ir-is");
        health.put("timestamp", "2025-08-07 09:45:21 UTC");
        health.put("service", "Enhanced PostService");
        health.put("controller", "Enhanced PostController");
        health.put("version", "2.0.0");
        health.put("features", Map.of(
                "vietnamese_ai_content", "ACTIVE",
                "long_form_generation", "ACTIVE",
                "content_scheduling", "ACTIVE",
                "metrics_tracking", "ACTIVE"
        ));
        return ResponseEntity.ok(health);
    }

    private Long getCurrentUserId(HttpServletRequest request) {
        // In production, extract from JWT token or session
        // For now, return user 1Ir-is ID
        return 1L;
    }
}