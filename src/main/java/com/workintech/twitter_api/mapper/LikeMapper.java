package com.workintech.twitter_api.mapper;

import com.workintech.twitter_api.dto.LikeRequest;
import com.workintech.twitter_api.dto.LikeResponse;
import com.workintech.twitter_api.entitiy.Like;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LikeMapper {

    private UserMapper userMapper;
    private TweetMapper tweetMapper;

    @Autowired LikeMapper(UserMapper userMapper, TweetMapper tweetMapper){
        this.userMapper = userMapper;
        this.tweetMapper = tweetMapper;
    }

    public Like toEntity(LikeRequest likeRequest){
        return new Like();
    }

    public LikeResponse toResponse(Like like){
        return new LikeResponse(like.getUser().getUserName(), like.getTweet().getText());
    }
}
