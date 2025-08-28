package com.workintech.twitter_api.repository;

import com.workintech.twitter_api.entitiy.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
