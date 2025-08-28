package com.workintech.twitter_api.service;

import com.workintech.twitter_api.dto.UserRequest;
import com.workintech.twitter_api.dto.UserResponse;
import com.workintech.twitter_api.entitiy.User;
import com.workintech.twitter_api.mapper.UserMapper;
import com.workintech.twitter_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(u -> userMapper.toResponse(u))
                .toList();
    }

    @Override
    public UserResponse findUserById(long id) {
        Optional<User> userOptional = userRepository.findById(id);

        if(userOptional.isPresent()){
            return userMapper.toResponse(userOptional.get());
        }
        return null;
    }

    @Override
    public UserResponse create(UserRequest userRequest) {
        User user = userRepository.save(userMapper.toEntity(userRequest));
        return userMapper.toResponse(user);
    }

    @Override
    public UserResponse replaceOrCreate(long id, UserRequest userRequest) {
        User user = userMapper.toEntity(userRequest);
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isPresent()){
            user.setId(id);
            return userMapper.toResponse(userRepository.save(user));
        }

        return userMapper.toResponse(userRepository.save(user));
    }

    @Override
    public UserResponse update(long id, UserRequest userRequest) {
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            user = userMapper.updateEntity(user, userRequest);
            return userMapper.toResponse(user);
        }
        return null;
    }

    @Override
    public void delete(long id) {
        userRepository.deleteById(id);
    }
}