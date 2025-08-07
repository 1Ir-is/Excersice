package com.example.server.service.impl;

import com.example.server.dto.*;
import com.example.server.enums.PostStatus;
import com.example.server.enums.TopicStatus;
import com.example.server.model.Post;
import com.example.server.model.Topic;
import com.example.server.repository.IPostRepository;
import com.example.server.repository.ITopicRepository;
import com.example.server.service.GPTService;
import com.example.server.service.PostService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
public class PostServiceImpl implements PostService {

    private final IPostRepository postRepository;
    private final ITopicRepository topicRepository;
    private final GPTService gptService;
    private final ModelMapper modelMapper;

    public PostServiceImpl(IPostRepository postRepository, ITopicRepository topicRepository,
                           GPTService gptService, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.topicRepository = topicRepository;
        this.gptService = gptService;
        this.modelMapper = modelMapper;
    }

    @Override
    @Async
    public CompletableFuture<List<PostResponseDTO>> generateContentForTopic(ContentGenerationRequestDTO request) {
        try {
            // Validate topic exists and is approved
            Topic topic = topicRepository.findById(request.getTopicId())
                    .orElseThrow(() -> new EntityNotFoundException("Topic not found"));

            if (!TopicStatus.APPROVED.equals(topic.getStatus())) {
                throw new IllegalArgumentException("Topic must be approved before generating content");
            }

            List<Post> posts = new ArrayList<>();
            long startTime = System.currentTimeMillis();

            for (int i = 0; i < request.getNumberOfPosts(); i++) {
                log.info("Generating Vietnamese content {}/{} for topic '{}'",
                        i + 1, request.getNumberOfPosts(), topic.getName());

                long postStartTime = System.currentTimeMillis();
                String content;

                // Choose generation method based on content type and target word count
                if (isLongFormContent(request)) {
                    content = gptService.generateLongFormContent(topic, request).get();
                } else {
                    content = gptService.generateContentFromTopic(
                            topic,
                            request.getTone(),
                            request.getContentType(),
                            buildEnhancedInstructions(request)
                    ).get();
                }

                long postEndTime = System.currentTimeMillis();
                Post post = createPostFromContent(content, topic, request, i + 1, postEndTime - postStartTime);
                posts.add(post);

                // Small delay to avoid rate limiting
                Thread.sleep(1000);
            }

            List<Post> savedPosts = postRepository.saveAll(posts);

            List<PostResponseDTO> responseDTOs = savedPosts.stream()
                    .map(this::mapPostToDTO)
                    .collect(Collectors.toList());

            long totalTime = System.currentTimeMillis() - startTime;
            log.info("Successfully generated {} Vietnamese posts for topic {} in {}ms",
                    responseDTOs.size(), request.getTopicId(), totalTime);

            return CompletableFuture.completedFuture(responseDTOs);

        } catch (Exception e) {
            log.error("Error generating Vietnamese content for topic {}: {}",
                    request.getTopicId(), e.getMessage(), e);
            throw new RuntimeException("Failed to generate Vietnamese content", e);
        }
    }

    @Override
    public PostResponseDTO updatePostContent(Long postId, String newContent, Long userId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Post not found"));

        post.setContentText(newContent);
        post.setUserId(userId);
        post.setAiGenerated(false); // User modified, no longer purely AI-generated

        // Extract title from updated content
        post.setTitle(extractTitleFromContent(newContent));

        // Update metrics
        post.setWordCount(countWords(newContent));
        post.setReadingTime(calculateReadingTime(post.getWordCount()));
        post.setHashtags(extractHashtags(newContent));
        post.setUpdatedAt(LocalDateTime.now());

        Post savedPost = postRepository.save(post);

        return mapPostToDTO(savedPost);
    }

    @Override
    public PostResponseDTO schedulePost(SchedulePostRequestDTO request) {
        Post post = postRepository.findById(request.getPostId())
                .orElseThrow(() -> new EntityNotFoundException("Post not found"));

        post.setSocialChannelId(request.getSocialChannelId());
        post.setScheduledTime(request.getScheduledTime());
        post.setStatus(PostStatus.SCHEDULED);

        if (request.getFinalContent() != null && !request.getFinalContent().trim().isEmpty()) {
            post.setContentText(request.getFinalContent());
            post.setTitle(extractTitleFromContent(request.getFinalContent()));
            post.setWordCount(countWords(request.getFinalContent()));
            post.setReadingTime(calculateReadingTime(post.getWordCount()));
            post.setHashtags(extractHashtags(request.getFinalContent()));
        }

        Post savedPost = postRepository.save(post);
        return mapPostToDTO(savedPost);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostResponseDTO> getPostsByTopicId(Long topicId) {
        List<Post> posts = postRepository.findByTopicIdOrderByCreatedAtDesc(topicId);
        return posts.stream()
                .map(this::mapPostToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public PostResponseDTO getPostById(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Post not found"));
        return mapPostToDTO(post);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostResponseDTO> getScheduledPostsByUser(Long userId) {
        List<PostStatus> scheduledStatuses = Arrays.asList(PostStatus.SCHEDULED);
        List<Post> posts = postRepository.findByUserIdAndStatusIn(userId, scheduledStatuses);
        return posts.stream()
                .map(this::mapPostToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deletePostById(Long postId) {
        if (!postRepository.existsById(postId)) {
            throw new EntityNotFoundException("Post not found");
        }
        postRepository.deleteById(postId);
    }

    @Override
    @Scheduled(fixedRate = 60000)
    public void publishScheduledPosts() {
        LocalDateTime now = LocalDateTime.now();
        List<Post> readyPosts = postRepository.findScheduledPostsReadyToPublish(PostStatus.SCHEDULED, now);

        for (Post post : readyPosts) {
            try {
                // Here you would integrate with social media APIs to actually publish
                // For now, we'll just update the status
                post.setStatus(PostStatus.PUBLISHED);
                post.setPublishedTime(now);
                postRepository.save(post);

                log.info("Published post {} at {}", post.getId(), now);

            } catch (Exception e) {
                log.error("Failed to publish post {}: {}", post.getId(), e.getMessage());
                post.setStatus(PostStatus.FAILED);
                postRepository.save(post);
            }
        }
    }

    private boolean isLongFormContent(ContentGenerationRequestDTO request) {
        if (request.getTargetWordCount() != null && request.getTargetWordCount() > 500) {
            return true;
        }

        return Arrays.asList("long_article", "blog_post", "detailed_guide", "white_paper", "case_study")
                .contains(request.getContentType());
    }

    private Post createPostFromContent(String content, Topic topic, ContentGenerationRequestDTO request,
                                       int index, long generationTimeMs) {
        Post post = new Post();
        post.setTopicId(topic.getId());
        post.setUserId(1L); // User 1Ir-is
        post.setContentText(content);
        post.setAiGenerated(true);
        post.setStatus(PostStatus.DRAFT);
        post.setGenerationTimestamp(LocalDateTime.now());
        post.setCreatedBy("1Ir-is");

        // Extract title from content
        post.setTitle(extractTitleFromContent(content));

        // Store generation metadata
        post.setContentTone(request.getTone());
        post.setContentType(request.getContentType());
        post.setTargetPlatform(request.getTargetPlatform());
        post.setTargetAudience(request.getTargetAudience());
        post.setTargetWordCount(request.getTargetWordCount());

        // Calculate content metrics
        Integer wordCount = countWords(content);
        post.setWordCount(wordCount);
        post.setReadingTime(calculateReadingTime(wordCount));
        post.setHashtags(extractHashtags(content));
        post.setEngagementScore(calculateEngagementScore(content, request));

        // Generate image prompt if requested
        if (Boolean.TRUE.equals(request.getIncludeImage())) {
            try {
                String imagePrompt = gptService.generateImagePromptFromContent(content).get();
                post.setContentImageUrl("https://placeholder.ai/generated_" + System.currentTimeMillis() + ".jpg");
                post.setImagePrompt(imagePrompt);
                log.info("Generated image prompt for post {}/{}", index, request.getNumberOfPosts());
            } catch (Exception e) {
                log.warn("Image prompt generation failed for post {}/{}: {}", index, request.getNumberOfPosts(), e.getMessage());
            }
        }

        return post;
    }

    private PostResponseDTO mapPostToDTO(Post post) {
        PostResponseDTO dto = modelMapper.map(post, PostResponseDTO.class);

        // Map topic if available
        if (post.getTopic() != null) {
            TopicResponseDTO topicDTO = modelMapper.map(post.getTopic(), TopicResponseDTO.class);
            dto.setTopic(topicDTO);
        }

        // Map social channel if available
        if (post.getSocialChannel() != null) {
            SocialChannelResponseDTO channelDTO = modelMapper.map(post.getSocialChannel(), SocialChannelResponseDTO.class);
            channelDTO.setTypeDisplayName(channelDTO.getTypeDisplayName());
            dto.setSocialChannel(channelDTO);
        }

        // Calculate derived fields
        dto.calculateDerivedFields();

        // Add metrics if available
        if (post.getWordCount() != null) {
            PostMetricsDTO metrics = new PostMetricsDTO();
            metrics.setQualityScore(post.getEngagementScore());
            metrics.setTokenUsage(calculateTokenUsage(post.getWordCount()));
            metrics.setGenerationTimeMs(3000L); // Estimated
            metrics.setWordsPerMinute(calculateWordsPerMinute(post.getWordCount(), 3000L));
            metrics.setAiConfidenceScore(92.5); // Estimated high confidence
            metrics.setReadabilityScore(8.0); // Good readability for Vietnamese
            metrics.setSeoScore(85); // Good SEO potential
            metrics.setEstimatedReach(2500); // Estimated social reach
            dto.setMetrics(metrics);
        }

        return dto;
    }

    private String buildEnhancedInstructions(ContentGenerationRequestDTO request) {
        StringBuilder instructions = new StringBuilder();

        if (request.getAdditionalInstructions() != null) {
            instructions.append(request.getAdditionalInstructions()).append(". ");
        }

        // Add audience-specific instructions
        if (request.getTargetAudience() != null) {
            instructions.append("Đối tượng mục tiêu: ").append(mapAudienceToVietnamese(request.getTargetAudience())).append(". ");
        }

        // Add platform-specific instructions
        if (request.getTargetPlatform() != null) {
            instructions.append("Nền tảng: ").append(request.getTargetPlatform()).append(". ");
        }

        instructions.append("Tạo ra nội dung chất lượng cao cho người Việt Nam. ");

        return instructions.toString();
    }

    private String extractTitleFromContent(String content) {
        if (content == null || content.trim().isEmpty()) return null;

        String[] lines = content.split("\n");
        for (String line : lines) {
            line = line.trim();
            if (!line.isEmpty() && (line.startsWith("#") || line.length() < 100)) {
                // Remove markdown headers and emojis for clean title
                return line.replaceAll("^#+\\s*", "").trim();
            }
        }

        // Fallback: use first 60 characters
        return content.substring(0, Math.min(60, content.length())).trim() + "...";
    }

    private Integer countWords(String content) {
        if (content == null || content.trim().isEmpty()) return 0;
        return content.trim().split("\\s+").length;
    }

    private Integer calculateReadingTime(Integer wordCount) {
        if (wordCount == null || wordCount == 0) return 1;

        // Average reading speed: 200-250 Vietnamese words per minute
        return Math.max(1, (int) Math.ceil(wordCount / 225.0));
    }

    private String extractHashtags(String content) {
        if (content == null) return "";

        return content.lines()
                .filter(line -> line.contains("#"))
                .map(line -> line.replaceAll("[^#\\w\\s]", ""))
                .collect(Collectors.joining(" "))
                .trim();
    }

    private Double calculateEngagementScore(String content, ContentGenerationRequestDTO request) {
        double score = 5.0; // Base score

        // Content quality indicators
        if (content.contains("🚀") || content.contains("✨")) score += 1.0;
        if (content.contains("?")) score += 0.5; // Questions engage
        if (content.contains("!")) score += 0.3; // Exclamations
        if (content.contains("#")) score += 0.5; // Hashtags

        // Length optimization
        int wordCount = countWords(content);
        if (wordCount >= 150 && wordCount <= 300) score += 1.0; // Optimal short-form
        if (wordCount >= 800 && wordCount <= 1500) score += 1.5; // Optimal long-form

        // Tone bonus
        if ("enthusiastic".equals(request.getTone())) score += 1.0;
        if ("professional".equals(request.getTone())) score += 0.8;

        // Content type bonus
        if (isLongFormContent(request)) score += 1.5; // Long-form bonus

        // Platform optimization
        if ("linkedin".equals(request.getTargetPlatform()) && "professional".equals(request.getTone())) {
            score += 0.5;
        }

        return Math.min(10.0, score); // Cap at 10
    }

    private Integer calculateTokenUsage(Integer wordCount) {
        if (wordCount == null) return 0;
        // Estimate: 1 Vietnamese word ≈ 1.5 tokens
        return (int) (wordCount * 1.5);
    }

    private Double calculateWordsPerMinute(Integer wordCount, Long timeMs) {
        if (wordCount == null || timeMs == null || timeMs == 0) return 0.0;
        double minutes = timeMs / 60000.0; // Convert to minutes
        return wordCount / minutes;
    }

    private String mapAudienceToVietnamese(String audience) {
        if (audience == null) return "người dùng phổ thông";

        return switch (audience.toLowerCase()) {
            case "business_owners" -> "chủ doanh nghiệp";
            case "young_professionals" -> "chuyên gia trẻ";
            case "students" -> "sinh viên";
            case "tech_enthusiasts" -> "người yêu công nghệ";
            case "executives" -> "lãnh đạo doanh nghiệp";
            case "entrepreneurs" -> "doanh nhân";
            default -> "người dùng phổ thông";
        };
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostResponseDTO> getPostsByContentType(String contentType) {
        List<Post> posts = postRepository.findByContentTypeOrderByCreatedAtDesc(contentType);
        return posts.stream()
                .map(this::mapPostToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostResponseDTO> getPostsByTargetPlatform(String targetPlatform) {
        List<Post> posts = postRepository.findByTargetPlatformOrderByCreatedAtDesc(targetPlatform);
        return posts.stream()
                .map(this::mapPostToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostResponseDTO> getLongFormPosts() {
        List<Post> posts = postRepository.findByWordCountGreaterThanOrderByCreatedAtDesc(500);
        return posts.stream()
                .map(this::mapPostToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostResponseDTO> getPostsByEngagementScore(Double minScore) {
        List<Post> posts = postRepository.findByEngagementScoreGreaterThanEqualOrderByEngagementScoreDesc(minScore);
        return posts.stream()
                .map(this::mapPostToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostResponseDTO> getRecentPostsByUser(Long userId, int limit) {
        List<Post> posts = postRepository.findTop10ByUserIdOrderByCreatedAtDesc(userId);
        return posts.stream()
                .limit(limit)
                .map(this::mapPostToDTO)
                .collect(Collectors.toList());
    }
}