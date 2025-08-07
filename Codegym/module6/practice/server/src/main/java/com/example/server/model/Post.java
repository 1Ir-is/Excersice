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

    // ========== CONTENT FIELDS ==========
    @Column(name = "title", length = 255)
    private String title;

    @Column(name = "content_text", columnDefinition = "TEXT")
    private String contentText;

    @Column(name = "content_image_url")
    private String contentImageUrl;

    @Column(name = "image_prompt", columnDefinition = "TEXT")
    private String imagePrompt;

    // ========== GENERATION METADATA ==========
    @Column(name = "ai_generated")
    private Boolean aiGenerated = true;

    @Column(name = "content_tone", length = 50)
    private String contentTone;

    @Column(name = "content_type", length = 50)
    private String contentType;

    @Column(name = "target_platform", length = 50)
    private String targetPlatform;

    @Column(name = "target_audience", length = 50)
    private String targetAudience;

    @Column(name = "industry", length = 50)
    private String industry;

    // ========== CONTENT METRICS ==========
    @Column(name = "word_count")
    private Integer wordCount;

    @Column(name = "reading_time")
    private Integer readingTime; // in minutes

    @Column(name = "engagement_score")
    private Double engagementScore;

    @Column(name = "hashtags", columnDefinition = "TEXT")
    private String hashtags;

    @Column(name = "target_word_count")
    private Integer targetWordCount;

    // ========== STATUS & SCHEDULING ==========
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private PostStatus status = PostStatus.DRAFT;

    @Column(name = "scheduled_time")
    private LocalDateTime scheduledTime;

    @Column(name = "published_time")
    private LocalDateTime publishedTime;

    // ========== AUDIT FIELDS ==========
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "created_by")
    private String createdBy = "1Ir-is"; // User who created this post

    @Column(name = "generation_timestamp")
    private LocalDateTime generationTimestamp;

    // ========== RELATIONSHIPS ==========
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id", insertable = false, updatable = false)
    private Topic topic;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "social_channel_id", insertable = false, updatable = false)
    private SocialChannel socialChannel;

    // ========== HELPER METHODS ==========
    @PrePersist
    public void prePersist() {
        if (this.generationTimestamp == null) {
            this.generationTimestamp = LocalDateTime.now();
        }
        if (this.createdBy == null) {
            this.createdBy = "1Ir-is";
        }
    }

    public boolean isLongForm() {
        return this.wordCount != null && this.wordCount > 500;
    }

    public String getContentTypeDisplayName() {
        if (contentType == null) return "Bài đăng mạng xã hội";

        return switch (contentType.toLowerCase()) {
            case "social_post" -> "Bài đăng mạng xã hội";
            case "long_article" -> "Bài viết dài chuyên sâu";
            case "blog_post" -> "Blog post chi tiết";
            case "detailed_guide" -> "Hướng dẫn chi tiết";
            case "white_paper" -> "Báo cáo chuyên môn";
            case "case_study" -> "Nghiên cứu tình huống";
            default -> "Nội dung marketing";
        };
    }
}