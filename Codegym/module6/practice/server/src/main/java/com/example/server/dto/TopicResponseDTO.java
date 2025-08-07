package com.example.server.dto;

import com.example.server.enums.TopicStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopicResponseDTO {
    private Long id;
    private String name;
    private String description;
    private Boolean aiGenerated;
    private TopicStatus status;
    private LocalDateTime createdAt;
    private Long campaignId;
}
