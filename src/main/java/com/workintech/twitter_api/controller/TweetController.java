package com.workintech.twitter_api.controller;

import com.workintech.twitter_api.dto.TweetRequest;
import com.workintech.twitter_api.dto.TweetResponse;

import com.workintech.twitter_api.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tweet")
public class TweetController {
    private TweetService tweetService;

    @Autowired
    public TweetController(TweetService tweetService){
        this.tweetService = tweetService;
    }

    @GetMapping("/user/{userId}")
    public List<TweetResponse> findByUserId(@PathVariable long userId){
        return tweetService.findByUserId(userId);
    }

    @GetMapping("/{id}")
    public TweetResponse findById(@PathVariable long id){
        return tweetService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TweetResponse create(@Validated @RequestBody TweetRequest tweetRequest){
        return tweetService.create(tweetRequest);
    }

    @PutMapping("/{id}")
    public TweetResponse update(@PathVariable long id, @Validated @RequestBody TweetRequest tweetRequest){
        return tweetService.update(id, tweetRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        tweetService.delete(id);
    }
}
