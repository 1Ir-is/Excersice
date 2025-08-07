package com.example.server.service.impl;

import com.example.server.dto.GPTMessage;
import com.example.server.dto.GPTRequestDTO;
import com.example.server.dto.GPTResponseDTO;
import com.example.server.model.Campaign;
import com.example.server.model.Topic;
import com.example.server.service.GPTService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
@ConditionalOnProperty(name = "app.gpt.mock.enabled", havingValue = "false")
public class GPTServiceImpl implements GPTService {

    @Value("${openai.api.key}")
    private String openaiApiKey;

    @Value("${openai.api.url}")
    private String openaiApiUrl;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public GPTServiceImpl(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
        log.info("🇻🇳 Vietnamese GPTService initialized for user 1Ir-is at 2025-08-07 07:54:54");
    }

    @Override
    public CompletableFuture<String> generateTopicsFromCampaign(Campaign campaign, Integer numberOfTopics, String additionalInstructions) {
        try {
            log.info("🇻🇳 Generating {} Vietnamese topics for campaign '{}' for user 1Ir-is", numberOfTopics, campaign.getName());

            String prompt = buildVietnameseTopicGenerationPrompt(campaign, numberOfTopics, additionalInstructions);

            GPTRequestDTO requestDTO = new GPTRequestDTO();
            requestDTO.setModel("gpt-3.5-turbo");
            requestDTO.setMax_tokens(1500); // Increase for Vietnamese content
            requestDTO.setTemperature(0.7);
            requestDTO.setMessages(Arrays.asList(
                    new GPTMessage("system",
                            "Bạn là một chuyên gia marketing người Việt Nam với 10 năm kinh nghiệm. " +
                                    "Bạn hiểu rõ thị trường Việt Nam, văn hóa, ngôn ngữ và hành vi tiêu dùng. " +
                                    "Hãy tạo các chủ đề marketing bằng tiếng Việt thuần túy, phù hợp với người Việt. " +
                                    "QUAN TRỌNG: Chỉ trả lời bằng tiếng Việt, không dùng tiếng Anh."
                    ),
                    new GPTMessage("user", prompt)
            ));

            GPTResponseDTO responseDTO = callGPTAPI(requestDTO).get();
            String content = responseDTO.getChoices().get(0).getMessage().getContent();

            log.info("✅ Successfully generated Vietnamese topics for user 1Ir-is");
            log.debug("Generated content: {}", content);

            return CompletableFuture.completedFuture(content);

        } catch (Exception e) {
            log.error("❌ Error generating Vietnamese topics for user 1Ir-is: {}", e.getMessage(), e);
            throw new RuntimeException("Không thể tạo ra các chủ đề tiếng Việt", e);
        }
    }

    @Override
    public CompletableFuture<String> generateContentFromTopic(Topic topic, String tone, String contentType, String additionalInstructions) {
        try {
            log.info("🇻🇳 Generating Vietnamese {} content for topic '{}' for user 1Ir-is",
                    contentType, topic.getName());

            String prompt = buildVietnameseContentGenerationPrompt(topic, tone, contentType, additionalInstructions);

            GPTRequestDTO requestDTO = new GPTRequestDTO();
            requestDTO.setModel("gpt-3.5-turbo");
            requestDTO.setMax_tokens(1000);
            requestDTO.setTemperature(0.8);
            requestDTO.setMessages(Arrays.asList(
                    new GPTMessage("system",
                            "Bạn là một copywriter chuyên nghiệp người Việt Nam, chuyên tạo nội dung marketing tiếng Việt. " +
                                    "Bạn viết theo phong cách người Việt, sử dụng từ ngữ thân thiện, dễ hiểu. " +
                                    "QUAN TRỌNG: Chỉ viết bằng tiếng Việt, không dùng tiếng Anh trừ khi cần thiết cho hashtag."
                    ),
                    new GPTMessage("user", prompt)
            ));

            GPTResponseDTO responseDTO = callGPTAPI(requestDTO).get();
            String content = responseDTO.getChoices().get(0).getMessage().getContent();

            log.info("✅ Successfully generated Vietnamese content for user 1Ir-is");
            return CompletableFuture.completedFuture(content);

        } catch (Exception e) {
            log.error("❌ Error generating Vietnamese content for user 1Ir-is: {}", e.getMessage(), e);
            throw new RuntimeException("Không thể tạo nội dung tiếng Việt", e);
        }
    }

    @Override
    public CompletableFuture<String> generateImagePromptFromContent(String content) {
        try {
            log.info("🇻🇳 Generating image prompt for Vietnamese content for user 1Ir-is");

            String prompt = String.format(
                    "Tạo một prompt chi tiết bằng TIẾNG ANH để generate hình ảnh minh họa cho nội dung marketing tiếng Việt sau:\n\n" +
                            "NỘI DUNG: %s\n\n" +
                            "YÊU CẦU CHO PROMPT:\n" +
                            "- Mô tả hình ảnh bằng tiếng Anh chuyên nghiệp\n" +
                            "- Phong cách hiện đại, phù hợp với thị trường Việt Nam\n" +
                            "- Màu sắc: xanh dương (#1976d2), trắng, xám nhẹ\n" +
                            "- Composition cho social media (16:9 hoặc 1:1)\n" +
                            "- Professional, clean, modern style\n" +
                            "- Avoid text in image\n\n" +
                            "CHỈ TRẢ VỀ PROMPT TIẾNG ANH, KHÔNG GIẢI THÍCH THÊM:",
                    content
            );

            GPTRequestDTO requestDTO = new GPTRequestDTO();
            requestDTO.setModel("gpt-3.5-turbo");
            requestDTO.setMax_tokens(300);
            requestDTO.setTemperature(0.6);
            requestDTO.setMessages(Arrays.asList(
                    new GPTMessage("system",
                            "Bạn là chuyên gia tạo prompt cho AI image generation. " +
                                    "Hãy tạo prompt tiếng Anh ngắn gọn, chính xác cho DALL-E hoặc Midjourney."
                    ),
                    new GPTMessage("user", prompt)
            ));

            GPTResponseDTO responseDTO = callGPTAPI(requestDTO).get();
            String imagePrompt = responseDTO.getChoices().get(0).getMessage().getContent();

            log.info("✅ Successfully generated image prompt for user 1Ir-is");
            return CompletableFuture.completedFuture(imagePrompt);

        } catch (Exception e) {
            log.error("❌ Error generating image prompt for user 1Ir-is: {}", e.getMessage(), e);
            throw new RuntimeException("Không thể tạo image prompt", e);
        }
    }

    @Override
    public CompletableFuture<GPTResponseDTO> callGPTAPI(GPTRequestDTO request) {
        try {
            log.debug("🔄 Calling OpenAI API for user 1Ir-is with model: {}", request.getModel());

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(openaiApiKey);

            HttpEntity<GPTRequestDTO> entity = new HttpEntity<>(request, headers);
            ResponseEntity<GPTResponseDTO> response = restTemplate.postForEntity(openaiApiUrl, entity, GPTResponseDTO.class);

            GPTResponseDTO responseBody = response.getBody();
            if (responseBody != null && responseBody.getUsage() != null) {
                log.info("📊 Token usage for user 1Ir-is: {} total tokens",
                        responseBody.getUsage().getTotal_tokens());
            }

            return CompletableFuture.completedFuture(responseBody);

        } catch (Exception e) {
            log.error("❌ OpenAI API call failed for user 1Ir-is: {}", e.getMessage(), e);
            throw new RuntimeException("Không thể gọi OpenAI API", e);
        }
    }

    private String buildVietnameseTopicGenerationPrompt(Campaign campaign, Integer numberOfTopics, String additionalInstruction) {
        StringBuilder prompt = new StringBuilder();

        prompt.append("🎯 NHIỆM VỤ: Tạo ").append(numberOfTopics).append(" chủ đề marketing bằng TIẾNG VIỆT cho chiến dịch sau:\n\n");

        prompt.append("📋 THÔNG TIN CHIẾN DỊCH:\n");
        prompt.append("• Tên chiến dịch: ").append(campaign.getName()).append("\n");
        prompt.append("• Mô tả: ").append(campaign.getDescription()).append("\n\n");

        if (additionalInstruction != null && !additionalInstruction.trim().isEmpty()) {
            prompt.append("🎯 YÊU CẦU ĐẶC BIỆT:\n");
            prompt.append("• ").append(additionalInstruction).append("\n\n");
        }

        prompt.append("🇻🇳 YÊU CẦU CHO CHỦ ĐỀ:\n");
        prompt.append("• Viết hoàn toàn bằng tiếng Việt\n");
        prompt.append("• Phù hợp với văn hóa và thị trường Việt Nam\n");
        prompt.append("• Dễ hiểu, gần gũi với người Việt\n");
        prompt.append("• Có tính ứng dụng thực tế cao\n");
        prompt.append("• Trending và thu hút\n");
        prompt.append("• Phù hợp với mạng xã hội Việt Nam\n\n");

        prompt.append("📝 ĐỊNH DẠNG TRẢ VỀ (CHÍNH XÁC):\n");
        prompt.append("{\n");
        prompt.append("  \"topics\": [\n");
        prompt.append("    {\n");
        prompt.append("      \"name\": \"Tên chủ đề tiếng Việt ngắn gọn và hấp dẫn\",\n");
        prompt.append("      \"description\": \"Mô tả chi tiết bằng tiếng Việt về cách triển khai chủ đề, bao gồm key message và phương pháp tiếp cận\"\n");
        prompt.append("    }\n");
        prompt.append("  ]\n");
        prompt.append("}\n\n");

        prompt.append("⚠️ LƯU Ý QUAN TRỌNG:\n");
        prompt.append("- CHỈ sử dụng tiếng Việt cho name và description\n");
        prompt.append("- Không dịch máy, hãy viết tự nhiên như người Việt\n");
        prompt.append("- Tên chủ đề không quá 60 ký tự\n");
        prompt.append("- Mô tả chi tiết 100-200 ký tự\n");
        prompt.append("- Đảm bảo JSON format chính xác\n");

        return prompt.toString();
    }

    private String buildVietnameseContentGenerationPrompt(Topic topic, String tone, String contentType, String additionalInstructions) {
        StringBuilder prompt = new StringBuilder();

        String vietnameseTone = mapToneToVietnamese(tone);
        String vietnameseContentType = mapContentTypeToVietnamese(contentType);

        prompt.append("🎯 NHIỆM VỤ: Tạo nội dung ").append(vietnameseContentType).append(" bằng TIẾNG VIỆT\n\n");

        prompt.append("📋 THÔNG TIN CHỦ ĐỀ:\n");
        prompt.append("• Chủ đề: ").append(topic.getName()).append("\n");
        prompt.append("• Mô tả: ").append(topic.getDescription()).append("\n");
        prompt.append("• Tone: ").append(vietnameseTone).append("\n");
        prompt.append("• Loại nội dung: ").append(vietnameseContentType).append("\n\n");

        if (additionalInstructions != null && !additionalInstructions.trim().isEmpty()) {
            prompt.append("🎯 YÊU CẦU ĐẶC BIỆT:\n");
            prompt.append("• ").append(additionalInstructions).append("\n\n");
        }

        prompt.append("🇻🇳 YÊU CẦU NỘI DUNG:\n");
        prompt.append("• Viết hoàn toàn bằng tiếng Việt tự nhiên\n");
        prompt.append("• Hook mạnh mẽ để thu hút người đọc\n");
        prompt.append("• Thông điệp rõ ràng và có giá trị\n");
        prompt.append("• Call-to-action cụ thể bằng tiếng Việt\n");
        prompt.append("• Sử dụng emoji phù hợp\n");
        prompt.append("• Hashtags tiếng Việt và tiếng Anh phù hợp\n");
        prompt.append("• Phong cách giao tiếp thân thiện với người Việt\n");
        prompt.append("• Độ dài: 150-300 từ\n\n");

        prompt.append("💡 GỢI Ý CẤU TRÚC:\n");
        prompt.append("1. Hook thu hút (emoji + câu mở đầu ấn tượng)\n");
        prompt.append("2. Nội dung chính (giá trị + lợi ích)\n");
        prompt.append("3. Call-to-action rõ ràng\n");
        prompt.append("4. Hashtags phù hợp\n\n");

        prompt.append("⚠️ QUAN TRỌNG: Viết như một người Việt đang nói chuyện, không dịch máy!");

        return prompt.toString();
    }

    private String mapToneToVietnamese(String tone) {
        if (tone == null) return "chuyên nghiệp và thân thiện";

        return switch (tone.toLowerCase()) {
            case "professional" -> "chuyên nghiệp và uy tín";
            case "casual" -> "thân thiện và gần gũi";
            case "enthusiastic" -> "hào hứng và năng động";
            case "informative" -> "hữu ích và giáo dục";
            case "promotional" -> "khuyến mãi và hấp dẫn";
            case "inspirational" -> "truyền cảm hứng và tích cực";
            default -> "chuyên nghiệp và thân thiện";
        };
    }

    private String mapContentTypeToVietnamese(String contentType) {
        if (contentType == null) return "bài đăng mạng xã hội";

        return switch (contentType.toLowerCase()) {
            case "social_post" -> "bài đăng mạng xã hội";
            case "article" -> "bài viết chi tiết";
            case "promotion" -> "nội dung khuyến mãi";
            case "story" -> "story/reel ngắn";
            case "email" -> "email marketing";
            case "blog" -> "blog post";
            default -> "bài đăng mạng xã hội";
        };
    }
}