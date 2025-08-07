package com.example.server.service;

import com.example.server.dto.ContentGenerationRequestDTO;
import com.example.server.dto.PostResponseDTO;
import com.example.server.dto.SchedulePostRequestDTO;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface PostService {
    CompletableFuture<List<PostResponseDTO>> generateContentForTopic(ContentGenerationRequestDTO request);

    PostResponseDTO updatePostContent(Long postId, String newContent, Long userId);

    PostResponseDTO schedulePost(SchedulePostRequestDTO request);

    List<PostResponseDTO> getPostsByTopicId(Long topicId);

    PostResponseDTO getPostById(Long postId);

    List<PostResponseDTO> getScheduledPostsByUser(Long userId);

    void deletePostById(Long postId);

    void publishScheduledPosts();

    List<PostResponseDTO> getPostsByContentType(String contentType);

    List<PostResponseDTO> getPostsByTargetPlatform(String targetPlatform);

    List<PostResponseDTO> getLongFormPosts();

    List<PostResponseDTO> getPostsByEngagementScore(Double minScore);

    List<PostResponseDTO> getRecentPostsByUser(Long userId, int limit);
}
