package com.example.server.repository;

import com.example.server.enums.CampaignStatus;
import com.example.server.model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICampaignRepository extends JpaRepository<Campaign, Long> {
    List<Campaign> findByWorkspaceIdAndStatus(Long workspaceId, CampaignStatus status);

    @Query("SELECT c FROM Campaign c WHERE c.workspaceId = :workspaceId ORDER BY c.createdAt DESC")
    List<Campaign> findByWorkspaceIdOrderByCreatedAtDesc(@Param("workspaceId") Long workspaceId);
}
