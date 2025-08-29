package com.workintech.twitter_api.service;

import com.workintech.twitter_api.dto.TweetRequest;
import com.workintech.twitter_api.dto.TweetResponse;
import com.workintech.twitter_api.entitiy.Tweet;

import java.util.List;

public interface TweetService {
    List<TweetResponse> findByUserId(long id);
    TweetResponse findById(long id);
    TweetResponse create(TweetRequest tweetRequest);
    TweetResponse update(long id, TweetRequest tweetRequest);
    void delete(long id);
}


