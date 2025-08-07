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
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/topics")
@Slf4j
@CrossOrigin(origins = "*")
@Tag(name = "Quản lý chủ đề", description = "API tạo và quản lý chủ đề marketing (Task 9)")
public class TopicController {

    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
        log.info("TopicController khởi tạo thành công lúc {}", LocalDateTime.now());
    }

    @Operation(
            summary = "Tạo chủ đề marketing bằng AI",
            description = """
                    Tạo nhiều chủ đề marketing cho một chiến dịch bằng OpenAI GPT.               
                    **Cách hoạt động:**
                    1. Cung cấp ID chiến dịch và số lượng chủ đề cần tạo
                    2. AI phân tích bối cảnh chiến dịch và tạo chủ đề phù hợp
                    3. Chủ đề được lưu với trạng thái PENDING để người dùng duyệt
                    4. Người dùng có thể duyệt/chặn chủ đề trước khi dùng để tạo nội dung
                    
                    **Yêu cầu:** Chiến dịch phải tồn tại trong cơ sở dữ liệu
                    **Trạng thái chủ đề tạo ra:** PENDING (cần duyệt trước khi tạo nội dung)
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Tạo chủ đề thành công",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TopicResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Tham số yêu cầu không hợp lệ (ví dụ: số lượng chủ đề ngoài phạm vi)",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Không tìm thấy chiến dịch",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Lỗi hệ thống hoặc dịch vụ AI không khả dụng",
                    content = @Content(mediaType = "application/json")
            )
    })
    @PostMapping("/generate")
    public CompletableFuture<ResponseEntity<List<TopicResponseDTO>>> generateTopics(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Tham số yêu cầu tạo chủ đề",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TopicGenerationRequestDTO.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Công ty công nghệ",
                                            description = "Tạo chủ đề cho chiến dịch công nghệ",
                                            value = """
                                                    {
                                                        "campaignId": 1,
                                                        "numberOfTopics": 5,
                                                        "additionalInstructions": "Tập trung vào chủ đề công nghệ và đổi mới cho công ty phần mềm. Bao gồm AI, điện toán đám mây, chuyển đổi số."
                                                    }
                                                    """
                                    ),
                                    @ExampleObject(
                                            name = "Doanh nghiệp thương mại điện tử",
                                            description = "Tạo chủ đề cho chiến dịch TMĐT",
                                            value = """
                                                    {
                                                        "campaignId": 1,
                                                        "numberOfTopics": 3,
                                                        "additionalInstructions": "Tập trung vào mua sắm online, trải nghiệm khách hàng, khuyến mãi theo mùa và xu hướng thương mại di động."
                                                    }
                                                    """
                                    ),
                                    @ExampleObject(
                                            name = "Ngành y tế",
                                            description = "Tạo chủ đề cho ngành y tế",
                                            value = """
                                                    {
                                                        "campaignId": 1,
                                                        "numberOfTopics": 4,
                                                        "additionalInstructions": "Tập trung vào y tế từ xa, đổi mới chăm sóc bệnh nhân, công nghệ sức khoẻ và xu hướng chăm sóc sức khoẻ."
                                                    }
                                                    """
                                    )
                            }
                    )
            )
            @Valid @RequestBody TopicGenerationRequestDTO request) {

        log.info("=== YÊU CẦU TẠO CHỦ ĐỀ ===");
        log.info("ID chiến dịch: {}, Số lượng chủ đề: {}", request.getCampaignId(), request.getNumberOfTopics());
        log.info("Hướng dẫn bổ sung: {}", request.getAdditionalInstructions());
        log.info("TopicService khả dụng: {}", topicService != null);

        return topicService.generateTopicsForCampaign(request)
                .thenApply(topics -> {
                    log.info("Tạo thành công {} chủ đề cho chiến dịch {}", topics.size(), request.getCampaignId());
                    return ResponseEntity.ok(topics);
                })
                .exceptionally(ex -> {
                    log.error("Lỗi khi tạo chủ đề cho chiến dịch {}: {}", request.getCampaignId(), ex.getMessage(), ex);
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                });
    }

    @Operation(
            summary = "Lấy tất cả chủ đề của một chiến dịch",
            description = "Lấy tất cả chủ đề liên quan đến một ID chiến dịch, bao gồm trạng thái duyệt. Kết quả sắp xếp theo ngày tạo (mới nhất trước)."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lấy chủ đề thành công"),
            @ApiResponse(responseCode = "404", description = "Không tìm thấy chiến dịch hoặc không có chủ đề")
    })
    @GetMapping("/campaign/{campaignId}")
    public ResponseEntity<List<TopicResponseDTO>> getTopicsByCampaign(
            @Parameter(description = "ID chiến dịch để lấy chủ đề", example = "1")
            @PathVariable Long campaignId) {

        log.info("Lấy chủ đề cho chiến dịch: {}", campaignId);
        List<TopicResponseDTO> topics = topicService.getTopicsByCampaignId(campaignId);
        log.info("Tìm thấy {} chủ đề cho chiến dịch {}", topics.size(), campaignId);
        return ResponseEntity.ok(topics);
    }

    @Operation(
            summary = "Lấy chủ đề theo ID",
            description = "Lấy một chủ đề cụ thể theo ID với đầy đủ trạng thái và thời gian"
    )
    @GetMapping("/{topicId}")
    public ResponseEntity<TopicResponseDTO> getTopicById(
            @Parameter(description = "ID chủ đề", example = "1")
            @PathVariable Long topicId) {

        log.info("Lấy chủ đề: {}", topicId);
        TopicResponseDTO topic = topicService.getTopicById(topicId);
        return ResponseEntity.ok(topic);
    }

    @Operation(
            summary = "Duyệt chủ đề",
            description = """
                    Duyệt một chủ đề đang chờ để có thể dùng tạo nội dung.
                    
                    **Chuyển trạng thái:** PENDING → APPROVED
                    **Hiệu lực:** Chủ đề có thể dùng tạo nội dung
                    **Lưu ý:** Chỉ chủ đề trạng thái PENDING mới được duyệt
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Duyệt chủ đề thành công"),
            @ApiResponse(responseCode = "404", description = "Không tìm thấy chủ đề"),
            @ApiResponse(responseCode = "400", description = "Chủ đề không ở trạng thái chờ hoặc đã xử lý")
    })
    @PutMapping("/{topicId}/approve")
    public ResponseEntity<TopicResponseDTO> approveTopic(
            @Parameter(description = "ID chủ đề cần duyệt", example = "1")
            @PathVariable Long topicId,
            HttpServletRequest request) {

        Long userId = getCurrentUserId(request);
        log.info("Người dùng {} duyệt chủ đề {}", userId, topicId);

        TopicResponseDTO topic = topicService.approveTopicById(topicId, userId);
        log.info("Chủ đề {} đã được duyệt thành công bởi người dùng {}", topicId, userId);
        return ResponseEntity.ok(topic);
    }

    @Operation(
            summary = "Từ chối chủ đề",
            description = """
                    Từ chối một chủ đề đang chờ. Chủ đề bị từ chối sẽ không được dùng tạo nội dung.
                    
                    **Chuyển trạng thái:** PENDING → REJECTED
                    **Hiệu lực:** Chủ đề không thể dùng tạo nội dung
                    **Lưu ý:** Chỉ chủ đề trạng thái PENDING mới được từ chối
                    """
    )
    @PutMapping("/{topicId}/reject")
    public ResponseEntity<TopicResponseDTO> rejectTopic(
            @Parameter(description = "ID chủ đề cần từ chối", example = "2")
            @PathVariable Long topicId,
            HttpServletRequest request) {

        Long userId = getCurrentUserId(request);
        log.info("Người dùng {} từ chối chủ đề {}", userId, topicId);

        TopicResponseDTO topic = topicService.rejectTopicById(topicId, userId);
        log.info("Chủ đề {} đã bị từ chối bởi người dùng {}", topicId, userId);
        return ResponseEntity.ok(topic);
    }

    @Operation(
            summary = "Xoá chủ đề",
            description = """
                    Xoá vĩnh viễn một chủ đề và toàn bộ bài viết liên quan. Hành động này không thể hoàn tác.
                    
                    **Cảnh báo:** Thao tác này cũng xoá toàn bộ bài viết tạo từ chủ đề này
                    **Hiệu ứng dây chuyền:** Tất cả bài viết liên quan sẽ bị xoá
                    **Trường hợp sử dụng:** Dọn dẹp chủ đề không mong muốn
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Xoá chủ đề thành công"),
            @ApiResponse(responseCode = "404", description = "Không tìm thấy chủ đề")
    })
    @DeleteMapping("/{topicId}")
    public ResponseEntity<Void> deleteTopic(
            @Parameter(description = "ID chủ đề cần xoá", example = "3")
            @PathVariable Long topicId) {

        log.info("Xoá chủ đề: {}", topicId);
        topicService.deleteTopicById(topicId);
        log.info("Đã xoá thành công chủ đề {}", topicId);
        return ResponseEntity.noContent().build();
    }

    private Long getCurrentUserId(HttpServletRequest request) {
        // Lấy từ JWT token hoặc session
        // Tạm thời trả về user ID
        return 1L;
    }
}