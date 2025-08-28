package com.workintech.twitter_api.service;


import com.workintech.twitter_api.dto.UserRequest;
import com.workintech.twitter_api.dto.UserResponse;
import com.workintech.twitter_api.entitiy.User;

import java.util.List;

public interface UserService {
    List<UserResponse> getAllUsers();
    UserResponse findUserById(long id);
    UserResponse create(UserRequest userRequest);
    UserResponse replaceOrCreate(long id, UserRequest userRequest);
    UserResponse update(long id, UserRequest userRequest);
    void delete(long id);
}
