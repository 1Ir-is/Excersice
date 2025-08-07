package com.example.server.model;

import com.example.server.enums.PostStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "posts")
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "topic_id")
    private Long topicId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "social_channel_id")
    private Long socialChannelId;

    @Column(name = "content_context", columnDefinition = "TEXT")
    private String contentText;

    @Column(name = "content_image_url")
    private String contentImageUrl;

    @Column(name = "ai_generated")
    private Boolean aiGenerated = true;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private PostStatus status = PostStatus.DRAFT;

    @Column(name = "scheduled_time")
    private LocalDateTime scheduledTime;

    @Column(name = "published_time")
    private LocalDateTime publishedTime;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id", insertable = false, updatable = false)
    private Topic topic;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "social_channel_id", insertable = false, updatable = false)
    private SocialChannel socialChannel;
}
