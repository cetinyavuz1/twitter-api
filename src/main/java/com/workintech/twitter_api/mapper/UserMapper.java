package com.workintech.twitter_api.mapper;

import com.workintech.twitter_api.dto.UserRequest;
import com.workintech.twitter_api.dto.UserResponse;
import com.workintech.twitter_api.entitiy.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User toEntity(UserRequest userRequest){
        User user = new User();
        user.setUserName(userRequest.userName());
        user.setEmail(userRequest.email());
        user.setPassword(userRequest.password());
        return user;
    }

    public UserResponse toResponse(User user){
        return new UserResponse(user.getUserName(), user.getEmail());
    }

    public User updateEntity(User updatedUser, UserRequest userRequest){
        if(userRequest.userName() != null){
            updatedUser.setUserName(userRequest.userName());
        }

        if(userRequest.email() != null){
            updatedUser.setEmail(userRequest.email());
        }

        if(userRequest.password() != null){
            updatedUser.setPassword(userRequest.password());
        }

        return updatedUser;
    }
}
