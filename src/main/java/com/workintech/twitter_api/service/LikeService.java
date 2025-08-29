package com.workintech.twitter_api.service;

import com.workintech.twitter_api.dto.LikeRequest;
import com.workintech.twitter_api.dto.LikeResponse;
import com.workintech.twitter_api.mapper.LikeMapper;

public interface LikeService {
    LikeResponse create(LikeRequest likeRequest);
    void delete(long id);
}
