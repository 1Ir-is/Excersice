package com.example.server.dto;

import com.example.server.enums.PostStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponseDTO {
    private Long id;
    private String contentText;
    private String contentImageUrl;
    private Boolean aiGenerated;
    private PostStatus status;
    private LocalDateTime scheduledTime;
    private LocalDateTime createdAt;
    private TopicResponseDTO topic;
    private Long topicId;
}
