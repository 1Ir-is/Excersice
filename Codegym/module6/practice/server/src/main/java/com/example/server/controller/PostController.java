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

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/posts")
@Slf4j
@CrossOrigin(origins = "*")
@Tag(name = "Tạo nội dung nâng cao", description = "API tạo nội dung tiếng Việt (ngắn & dài) và lên lịch đăng bài")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @Operation(
            summary = "Tạo nội dung tiếng Việt bằng AI (Nâng cao)",
            description = """
                    Tạo nội dung tiếng Việt chất lượng cao từ chủ đề đã duyệt bằng OpenAI GPT.
                    ****
                    **Loại nội dung hỗ trợ:**
                    - Ngắn: social_post, story, email (150-400 từ)
                    - Trung bình: article, blog_post (400-800 từ)
                    - Dài: long_article, detailed_guide, white_paper, case_study (800-2000 từ)
                    
                    **Quy trình:**
                    1. Chọn chủ đề đã duyệt (trạng thái: APPROVED)
                    2. Cấu hình tham số nội dung (giọng điệu, loại, số từ)
                    3. AI tạo nội dung tiếng Việt có cấu trúc
                    4. Nội dung gồm tiêu đề, thân bài, hashtag, chỉ số
                    5. Tuỳ chọn tạo prompt hình ảnh
                    6. Xem lại và chỉnh sửa trước khi lên lịch
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tạo nội dung tiếng Việt thành công"),
            @ApiResponse(responseCode = "400", description = "Tham số không hợp lệ hoặc chủ đề chưa duyệt"),
            @ApiResponse(responseCode = "404", description = "Không tìm thấy chủ đề"),
            @ApiResponse(responseCode = "500", description = "Lỗi dịch vụ AI")
    })
    @PostMapping("/generate")
    public CompletableFuture<ResponseEntity<List<PostResponseDTO>>> generateContent(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Yêu cầu tạo nội dung",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ContentGenerationRequestDTO.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Bài đăng mạng xã hội tiếng Việt",
                                            description = "Tạo nội dung mạng xã hội tiếng Việt hấp dẫn",
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
                                            name = "Bài viết dài tiếng Việt",
                                            description = "Tạo bài viết kinh doanh tiếng Việt chi tiết",
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
                                            name = "Hướng dẫn chi tiết tiếng Việt",
                                            description = "Tạo hướng dẫn từng bước tiếng Việt toàn diện",
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
                                            name = "Case study tiếng Việt",
                                            description = "Tạo case study kinh doanh bằng tiếng Việt",
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

        return postService.generateContentForTopic(request)
                .thenApply(posts -> {
                    log.info("Tạo thành công {} bài viết tiếng Việt cho chủ đề {}",
                            posts.size(), request.getTopicId());
                    return ResponseEntity.ok(posts);
                })
                .exceptionally(ex -> {
                    log.error("Lỗi khi tạo nội dung tiếng Việt cho chủ đề {}: {}",
                            request.getTopicId(), ex.getMessage(), ex);
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                });
    }

    @Operation(
            summary = "Lên lịch đăng bài tiếng Việt",
            description = """
                    Lên lịch đăng bài tiếng Việt vào thời gian cụ thể trên mạng xã hội.
                    
                    **Nền tảng hỗ trợ:**
                    - Facebook,...

                    """
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lên lịch bài viết thành công"),
            @ApiResponse(responseCode = "400", description = "Tham số không hợp lệ hoặc thời gian đã qua"),
            @ApiResponse(responseCode = "404", description = "Không tìm thấy bài viết hoặc kênh mạng xã hội")
    })
    @PostMapping("/schedule")
    public ResponseEntity<PostResponseDTO> schedulePost(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Yêu cầu lên lịch đăng bài cho người dùng",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SchedulePostRequestDTO.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Lên lịch Facebook tiếng Việt",
                                            description = "Lên lịch nội dung tiếng Việt lên Facebook",
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
                                            name = "Lên lịch LinkedIn tiếng Việt",
                                            description = "Lên lịch nội dung chuyên nghiệp tiếng Việt lên LinkedIn",
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

        log.info("=== ĐANG LÊN LỊCH BÀI VIẾT TIẾNG VIỆT ===");
        log.info("Post ID: {}, Kênh: {}, Lịch đăng: {}",
                request.getPostId(), request.getSocialChannelId(), request.getScheduledTime());

        PostResponseDTO post = postService.schedulePost(request);
        log.info("Bài viết tiếng Việt {} đã được lên lịch thành công cho {}",
                post.getId(), request.getScheduledTime());
        return ResponseEntity.ok(post);
    }

    @Operation(
            summary = "Cập nhật nội dung bài viết tiếng Việt",
            description = "Cập nhật nội dung bài viết tiếng Việt. Người dùng có thể chỉnh sửa nội dung AI trước khi lên lịch. Đảm bảo chất lượng tiếng Việt."
    )
    @PutMapping("/{postId}/content")
    public ResponseEntity<PostResponseDTO> updatePostContent(
            @Parameter(description = "ID bài viết cần cập nhật", example = "48")
            @PathVariable Long postId,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Nội dung tiếng Việt mới cho bài viết",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Nội dung tiếng Việt cập nhật",
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

        String title = contentUpdate.get("title");
        String content = contentUpdate.get("content");
        String fullContent = (title != null ? title + "\n\n" : "") + (content != null ? content : "");

        PostResponseDTO post = postService.updatePostContent(postId, fullContent, userId);
        log.info("Cập nhật nội dung bài viết tiếng Việt {} thành công", postId);
        return ResponseEntity.ok(post);
    }

    @Operation(
            summary = "Lấy bài viết theo loại nội dung",
            description = "Lấy bài viết theo loại nội dung (social_post, long_article, blog_post, v.v.)"
    )
    @GetMapping("/content-type/{contentType}")
    public ResponseEntity<List<PostResponseDTO>> getPostsByContentType(
            @Parameter(description = "Loại nội dung", example = "long_article")
            @PathVariable String contentType) {

        log.info("Lấy bài viết theo loại nội dung: {}", contentType);
        List<PostResponseDTO> posts = postService.getPostsByContentType(contentType);
        log.info("Tìm thấy {} bài viết loại {}", posts.size(), contentType);
        return ResponseEntity.ok(posts);
    }

    @Operation(
            summary = "Lấy bài viết theo nền tảng",
            description = "Lấy bài viết theo nền tảng (facebook, linkedin, instagram, v.v.)"
    )
    @GetMapping("/platform/{platform}")
    public ResponseEntity<List<PostResponseDTO>> getPostsByPlatform(
            @Parameter(description = "Nền tảng", example = "linkedin")
            @PathVariable String platform) {

        log.info("Lấy bài viết theo nền tảng: {}", platform);
        List<PostResponseDTO> posts = postService.getPostsByTargetPlatform(platform);
        log.info("Tìm thấy {} bài viết cho nền tảng {}", posts.size(), platform);
        return ResponseEntity.ok(posts);
    }

    @Operation(
            summary = "Lấy bài viết dài",
            description = "Lấy tất cả bài viết dài (số từ > 500)"
    )
    @GetMapping("/long-form")
    public ResponseEntity<List<PostResponseDTO>> getLongFormPosts() {
        log.info("Lấy bài viết dài");
        List<PostResponseDTO> posts = postService.getLongFormPosts();
        log.info("Tìm thấy {} bài viết dài", posts.size());
        return ResponseEntity.ok(posts);
    }

    @Operation(
            summary = "Lấy bài viết có tương tác cao",
            description = "Lấy bài viết có điểm tương tác cao"
    )
    @GetMapping("/high-engagement")
    public ResponseEntity<List<PostResponseDTO>> getHighEngagementPosts(
            @Parameter(description = "Điểm tương tác tối thiểu", example = "8.0")
            @RequestParam(defaultValue = "8.0") Double minScore) {

        log.info("Lấy bài viết tương tác cao (điểm >= {})", minScore);
        List<PostResponseDTO> posts = postService.getPostsByEngagementScore(minScore);
        log.info("Tìm thấy {} bài viết tương tác cao", posts.size());
        return ResponseEntity.ok(posts);
    }

    @Operation(
            summary = "Lấy bài viết gần đây của người dùng",
            description = "Lấy các bài viết gần đây cho người dùng hiện tại"
    )
    @GetMapping("/recent")
    public ResponseEntity<List<PostResponseDTO>> getRecentPosts(
            @Parameter(description = "Số lượng bài viết trả về", example = "10")
            @RequestParam(defaultValue = "10") int limit,
            HttpServletRequest request) {

        Long userId = getCurrentUserId(request);
        log.info("Lấy {} bài viết gần đây cho người dùng 1Ir-is", limit);
        List<PostResponseDTO> posts = postService.getRecentPostsByUser(userId, limit);
        log.info("✅ Tìm thấy {} bài viết gần đây cho người dùng 1Ir-is", posts.size());
        return ResponseEntity.ok(posts);
    }

    @Operation(summary = "Lấy bài viết theo chủ đề", description = "Lấy tất cả bài viết tiếng Việt cho một chủ đề cụ thể")
    @GetMapping("/topic/{topicId}")
    public ResponseEntity<List<PostResponseDTO>> getPostsByTopic(
            @Parameter(description = "ID chủ đề", example = "35")
            @PathVariable Long topicId) {

        log.info("Lấy bài viết tiếng Việt cho chủ đề: {}", topicId);
        List<PostResponseDTO> posts = postService.getPostsByTopicId(topicId);
        log.info("Tìm thấy {} bài viết tiếng Việt cho chủ đề {}", posts.size(), topicId);
        return ResponseEntity.ok(posts);
    }

    @Operation(summary = "Lấy bài viết theo ID", description = "Lấy chi tiết bài viết tiếng Việt")
    @GetMapping("/{postId}")
    public ResponseEntity<PostResponseDTO> getPostById(
            @Parameter(description = "ID bài viết", example = "48")
            @PathVariable Long postId) {

        log.info("Lấy bài viết tiếng Việt: {}", postId);
        PostResponseDTO post = postService.getPostById(postId);
        return ResponseEntity.ok(post);
    }

    @Operation(summary = "Lấy bài viết đã lên lịch", description = "Lấy tất cả bài viết tiếng Việt đã lên lịch")
    @GetMapping("/scheduled")
    public ResponseEntity<List<PostResponseDTO>> getScheduledPosts(HttpServletRequest request) {
        Long userId = getCurrentUserId(request);
        log.info("Lấy bài viết tiếng Việt đã lên lịch");

        List<PostResponseDTO> posts = postService.getScheduledPostsByUser(userId);
        log.info("Tìm thấy {} bài viết tiếng Việt đã lên lịch", posts.size());
        return ResponseEntity.ok(posts);
    }

    @Operation(summary = "Xoá bài viết", description = "Xoá vĩnh viễn bài viết tiếng Việt")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Xoá bài viết thành công"),
            @ApiResponse(responseCode = "404", description = "Không tìm thấy bài viết")
    })
    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(
            @Parameter(description = "ID bài viết cần xoá", example = "48")
            @PathVariable Long postId) {

        log.info("Xoá bài viết tiếng Việt: {}", postId);
        postService.deletePostById(postId);
        log.info("Đã xoá thành công bài viết tiếng Việt {}", postId);
        return ResponseEntity.noContent().build();
    }

    private Long getCurrentUserId(HttpServletRequest request) {
        // Lấy từ JWT token hoặc session
        // Tạm thời trả về user ID
        return 1L;
    }
}