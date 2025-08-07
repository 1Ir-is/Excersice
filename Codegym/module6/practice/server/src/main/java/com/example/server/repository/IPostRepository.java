package com.example.server.repository;

import com.example.server.enums.PostStatus;
import com.example.server.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IPostRepository extends JpaRepository<Post, Long> {
    List<Post> findByTopicIdOrderByCreatedAtDesc(Long topicId);

    List<Post> findByTopicIdAndStatus(Long topicId, PostStatus status);

    @Query("SELECT p FROM Post p WHERE p.status = :status AND p.scheduledTime <= :currentTime")
    List<Post> findScheduledPostsReadyToPublish(@Param("status") PostStatus status,
                                                @Param("currentTime") LocalDateTime currentTime);

    @Query("SELECT p FROM Post p WHERE p.userId = :userId AND p.status IN :statuses ORDER BY p.createdAt DESC")
    List<Post> findByUserIdAndStatusIn(@Param("userId") Long userId,
                                       @Param("statuses") List<PostStatus> statuses);
}
