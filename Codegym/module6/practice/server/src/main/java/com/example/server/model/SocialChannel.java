package com.example.server.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "social_channels")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SocialChannel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "workspace_id")
    private Long workspaceId;

    @Column(name = "type")
    private String type;

    @Column(name = "channel_name")
    private String channelName;

    @Column(name = "channel_identifier")
    private String channelIdentifier;

    @Column(name = "access_token")
    private String accessToken;

    @Column(name = "status")
    private String status;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
