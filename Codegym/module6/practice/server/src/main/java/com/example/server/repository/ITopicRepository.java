package com.example.server.repository;

import com.example.server.enums.TopicStatus;
import com.example.server.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITopicRepository extends JpaRepository<Topic, Long> {
    List<Topic> findByCampaignIdOrderByCreatedAtDesc(Long campaignId);

    List<Topic> findByCampaignIdAndStatus(Long campaignId, TopicStatus status);

    @Query("SELECT t FROM Topic t WHERE t.campaignId = :campaignId AND t.aiGenerated = true")
    List<Topic> findAiGeneratedTopicsByCampaignId(@Param("campaignId") Long campaignId);
}
