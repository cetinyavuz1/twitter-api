package com.workintech.twitter_api.mapper;

import com.workintech.twitter_api.dto.TweetRequest;
import com.workintech.twitter_api.dto.TweetResponse;
import com.workintech.twitter_api.entitiy.Tweet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TweetMapper {
    private UserMapper userMapper;

    @Autowired
    public TweetMapper(UserMapper userMapper){
        this.userMapper = userMapper;
    }

    public Tweet toEntity(TweetRequest tweetRequest){
        Tweet tweet = new Tweet();
        tweet.setText(tweetRequest.text());
        return tweet;
    }

    public TweetResponse toResponse(Tweet tweet){
        return new TweetResponse(tweet.getUser().getUserName(), tweet.getText());
    }

    public Tweet updateEntity(Tweet updatedTweet, TweetRequest tweetRequest){
        if(tweetRequest.text() != null){
            updatedTweet.setText(tweetRequest.text());
        }
        return updatedTweet;
    }
}
