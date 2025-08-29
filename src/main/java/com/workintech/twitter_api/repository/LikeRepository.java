package com.workintech.twitter_api.repository;

import com.workintech.twitter_api.entitiy.Like;
import com.workintech.twitter_api.entitiy.Tweet;
import com.workintech.twitter_api.entitiy.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {
    boolean existsByUserAndTweet(User user, Tweet tweet);
}
