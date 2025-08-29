package com.workintech.twitter_api.service;

import com.workintech.twitter_api.dto.LikeRequest;
import com.workintech.twitter_api.dto.LikeResponse;
import com.workintech.twitter_api.entitiy.Like;
import com.workintech.twitter_api.entitiy.Tweet;
import com.workintech.twitter_api.entitiy.User;
import com.workintech.twitter_api.exceptions.AlreadyLikedException;
import com.workintech.twitter_api.exceptions.UserNotFoundException;
import com.workintech.twitter_api.mapper.LikeMapper;
import com.workintech.twitter_api.repository.LikeRepository;
import com.workintech.twitter_api.repository.TweetRepository;
import com.workintech.twitter_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class LikeServiceImpl implements LikeService{

    private LikeRepository likeRepository;
    private LikeMapper likeMapper;
    private UserRepository userRepository;
    private TweetRepository tweetRepository;

    @Autowired
    public LikeServiceImpl(LikeRepository likeRepository, LikeMapper likeMapper, UserRepository userRepository, TweetRepository tweetRepository){
        this.likeRepository = likeRepository;
        this.userRepository = userRepository;
        this.tweetRepository = tweetRepository;
        this.likeMapper = likeMapper;
    }


    @Override
    public LikeResponse create(LikeRequest likeRequest) {
        Like like = likeMapper.toEntity(likeRequest);
        Optional<User> userOptional = userRepository.findById(likeRequest.userId());
        Optional<Tweet> tweetOptional = tweetRepository.findById(likeRequest.tweetId());

        if(likeRepository.existsByUserAndTweet(userOptional.get(), tweetOptional.get())){
            throw new AlreadyLikedException("Tweet Zaten Beğenilmiş.");
        }

        if(userOptional.isPresent() && tweetOptional.isPresent()){
            like.setUser(userOptional.get());
            like.setTweet(tweetOptional.get());
            likeRepository.save(like);
            return likeMapper.toResponse(like);
        } else {
            throw new UserNotFoundException("Kullanıcı ya da Tweet bulunamadı.");
        }
    }

    @Override
    public void delete(long id) {
        likeRepository.deleteById(id);
    }
}