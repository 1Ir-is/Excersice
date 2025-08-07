package com.example.server.service.impl;

import com.example.server.dto.ContentGenerationRequestDTO;
import com.example.server.dto.PostResponseDTO;
import com.example.server.dto.SchedulePostRequestDTO;
import com.example.server.dto.TopicResponseDTO;
import com.example.server.enums.PostStatus;
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

    public PostServiceImpl(IPostRepository postRepository, ITopicRepository topicRepository, GPTService gptService, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.topicRepository = topicRepository;
        this.gptService = gptService;
        this.modelMapper = modelMapper;
    }


    @Override
    @Async
    public CompletableFuture<List<PostResponseDTO>> generateContentForTopic(ContentGenerationRequestDTO request) {
        try {
            Topic topic = topicRepository.findById(request.getTopicId())
                    .orElseThrow(() -> new EntityNotFoundException("Topic not found"));

            List<Post> posts = new ArrayList<>();

            for (int i = 0; i < request.getNumberOfPosts(); i++) {
                String content = gptService.generateContentFromTopic(
                        topic,
                        request.getTone(),
                        request.getContentType(),
                        request.getAdditionalInstructions()
                ).get();

                Post post = new Post();
                post.setTopicId(topic.getId());
                post.setContentText(content);
                post.setAiGenerated(true);
                post.setStatus(PostStatus.DRAFT);

                // Generate image URL if requested
                if (request.getIncludeImage()) {
                    String imagePrompt = gptService.generateImagePromptFromContent(content).get();
                    // Here you would integrate with an image generation service
                    // For now, we'll just set a placeholder
                    post.setContentImageUrl("https://placeholder.image.url/generated_" + System.currentTimeMillis() + ".jpg");
                }

                posts.add(post);
            }

            List<Post> savedPosts = postRepository.saveAll(posts);

            List<PostResponseDTO> responseDTOs = savedPosts.stream()
                    .map(post -> {
                        PostResponseDTO dto = modelMapper.map(post, PostResponseDTO.class);
                        dto.setTopic(modelMapper.map(topic, TopicResponseDTO.class));
                        return dto;
                    })
                    .collect(Collectors.toList());

            return CompletableFuture.completedFuture(responseDTOs);

        } catch (Exception e) {
            log.error("Error generating content for topic {}: {}", request.getTopicId(), e.getMessage());
            throw new RuntimeException("Failed to generate content", e);
        }
    }

    @Override
    public PostResponseDTO updatePostContent(Long postId, String newContent, Long userId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Post not found"));

        post.setContentText(newContent);
        post.setUserId(userId);
        post.setAiGenerated(false); // User modified, no longer purely AI-generated

        Post savedPost = postRepository.save(post);
        return modelMapper.map(savedPost, PostResponseDTO.class);
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
        }

        Post savedPost = postRepository.save(post);
        return modelMapper.map(savedPost, PostResponseDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostResponseDTO> getPostsByTopicId(Long topicId) {
        List<Post> posts = postRepository.findByTopicIdOrderByCreatedAtDesc(topicId);
        return posts.stream()
                .map(post -> modelMapper.map(post, PostResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public PostResponseDTO getPostById(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Post not found"));
        return modelMapper.map(post, PostResponseDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostResponseDTO> getScheduledPostsByUser(Long userId) {
        List<PostStatus> scheduledStatuses = Arrays.asList(PostStatus.SCHEDULED);
        List<Post> posts = postRepository.findByUserIdAndStatusIn(userId, scheduledStatuses);
        return posts.stream()
                .map(post -> modelMapper.map(post, PostResponseDTO.class))
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
}
