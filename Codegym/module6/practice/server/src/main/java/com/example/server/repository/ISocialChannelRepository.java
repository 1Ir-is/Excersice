package com.example.server.repository;

import com.example.server.model.SocialChannel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISocialChannelRepository extends JpaRepository<SocialChannel, Long> {
    List<SocialChannel> findByWorkspaceId(Long workspaceId);

    List<SocialChannel> findByWorkspaceIdAndStatus(Long workspaceId, String status);
}
