package com.workintech.twitter_api.controller;

import com.workintech.twitter_api.dto.LikeRequest;
import com.workintech.twitter_api.dto.LikeResponse;
import com.workintech.twitter_api.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/like")
public class LikeController {

    private LikeService likeService;

    @Autowired
    public LikeController(LikeService likeService){
        this.likeService = likeService;
    }

    @PostMapping
    public LikeResponse create(@Validated @RequestBody LikeRequest likeRequest){
       return likeService.create(likeRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        likeService.delete(id);
    }
}