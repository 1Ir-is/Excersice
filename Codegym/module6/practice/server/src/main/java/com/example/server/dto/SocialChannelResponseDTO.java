package com.example.server.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Social media channel information")
public class SocialChannelResponseDTO {

    @Schema(description = "Channel ID", example = "1")
    private Long id;

    @Schema(description = "Workspace ID", example = "1")
    private Long workspaceId;

    @Schema(description = "Channel type", example = "facebook")
    private String type;

    @Schema(description = "Channel display name", example = "Facebook Page - Marketing AI")
    private String channelName;

    @Schema(description = "Channel identifier", example = "@marketingai_vn")
    private String channelIdentifier;

    @Schema(description = "Connection status", example = "active")
    private String status;

    @Schema(description = "Channel creation timestamp")
    private LocalDateTime createdAt;

    @Schema(description = "Last update timestamp")
    private LocalDateTime updatedAt;

    @Schema(description = "Is channel currently active", example = "true")
    private Boolean isActive;

    @Schema(description = "Channel type display name", example = "Facebook")
    private String typeDisplayName;

    public String getTypeDisplayName() {
        if (type == null) return "Unknown";

        return switch (type.toLowerCase()) {
            case "facebook" -> "Facebook";
            case "instagram" -> "Instagram";
            case "linkedin" -> "LinkedIn";
            case "twitter" -> "Twitter";
            case "tiktok" -> "TikTok";
            case "youtube" -> "YouTube";
            default -> type.substring(0, 1).toUpperCase() + type.substring(1);
        };
    }
}