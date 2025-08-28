package com.workintech.twitter_api.repository;

import com.workintech.twitter_api.entitiy.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
