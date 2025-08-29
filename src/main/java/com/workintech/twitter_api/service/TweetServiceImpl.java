package com.workintech.twitter_api.service;

import com.workintech.twitter_api.dto.TweetRequest;
import com.workintech.twitter_api.dto.TweetResponse;
import com.workintech.twitter_api.entitiy.Tweet;
import com.workintech.twitter_api.entitiy.User;
import com.workintech.twitter_api.exceptions.TweetNotFoundException;
import com.workintech.twitter_api.exceptions.UserNotFoundException;
import com.workintech.twitter_api.mapper.TweetMapper;
import com.workintech.twitter_api.repository.TweetRepository;
import com.workintech.twitter_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TweetServiceImpl implements TweetService{
    private TweetRepository tweetRepository;
    private UserRepository userRepository;
    private TweetMapper tweetMapper;

    @Autowired
    public TweetServiceImpl(TweetRepository tweetRepository, UserRepository userRepository, TweetMapper tweetMapper){
        this.tweetRepository = tweetRepository;
        this.userRepository = userRepository;
        this.tweetMapper = tweetMapper;
    }


    @Override
    public List<TweetResponse> findByUserId(long id) {
        return tweetRepository.findByUserId(id).stream().map(t -> tweetMapper.toResponse(t)).toList();
    }

    @Override
    public TweetResponse findById(long id) {
        Optional<Tweet> tweetOptional = tweetRepository.findById(id);
        if(tweetOptional.isPresent()){
            return tweetMapper.toResponse(tweetOptional.get());
        }else {
            throw new TweetNotFoundException("Tweet bulunamadı");
        }
    }

    @Override
    public TweetResponse create(TweetRequest tweetRequest) {
        Tweet tweet = tweetMapper.toEntity(tweetRequest);
        Optional<User> userOptional = userRepository.findById(tweetRequest.userId());
        if(userOptional.isPresent()){
            tweet.setUser(userOptional.get());
            tweet.setText(tweetRequest.text());
            tweet = tweetRepository.save(tweet);
            return tweetMapper.toResponse(tweet);
        }else {
            throw new UserNotFoundException("Kullanıcı bulunamadı");
        }

    }

    @Override
    public TweetResponse update(long id, TweetRequest tweetRequest) {
        Tweet tweetToUpdate = tweetRepository.findById(id).orElseThrow(() -> new TweetNotFoundException("Tweet Bulunamadı"));
        tweetToUpdate = tweetMapper.updateEntity(tweetToUpdate, tweetRequest);
        tweetRepository.save(tweetToUpdate);
        return tweetMapper.toResponse(tweetToUpdate);
    }

    @Override
    public void delete(long id) {
        tweetRepository.deleteById(id);
    }
}
