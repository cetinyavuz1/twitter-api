package com.workintech.twitter_api.controller;

import com.workintech.twitter_api.dto.UserRequest;
import com.workintech.twitter_api.dto.UserResponse;
import com.workintech.twitter_api.entitiy.User;
import com.workintech.twitter_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public List<UserResponse> getAll(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserResponse get(@PathVariable("id") Long id){
        return userService.findUserById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse create(@Validated @RequestBody UserRequest userRequest){
        return userService.create(userRequest);
    }

    @PutMapping("/{id}")
    public UserResponse replaceOrCreate(@PathVariable long id, @Validated @RequestBody UserRequest userRequest){
        return userService.replaceOrCreate(id, userRequest);
    }

    @PatchMapping("/{id}")
    public UserResponse update(@PathVariable long id, @Validated @RequestBody UserRequest userRequest){
        return userService.update(id, userRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        userService.delete(id);
    }
}
