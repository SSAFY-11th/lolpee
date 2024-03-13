package com.lolpee.server.domain.follow.repository;

import com.lolpee.server.domain.follow.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, Long> {
}
