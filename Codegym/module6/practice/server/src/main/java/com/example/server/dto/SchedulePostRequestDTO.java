package com.example.server.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchedulePostRequestDTO {
    @NotNull(message = "Post ID is required")
    private Long postId;

    @NotNull(message = "Social channel ID is required")
    private Long socialChannelId;

    @NotNull(message = "Scheduled Time is required")
    @Future(message = "Scheduled Time must be in the future")
    private LocalDateTime scheduledTime;

    // co the modify AI content
    private String finalContent;
}
