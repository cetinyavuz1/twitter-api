package com.workintech.twitter_api.repository;

import com.workintech.twitter_api.entitiy.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {
}
