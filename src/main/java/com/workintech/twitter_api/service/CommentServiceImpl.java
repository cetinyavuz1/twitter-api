package com.workintech.twitter_api.service;

import com.workintech.twitter_api.dto.CommentRequest;
import com.workintech.twitter_api.dto.CommentResponse;
import com.workintech.twitter_api.entitiy.Comment;
import com.workintech.twitter_api.entitiy.Tweet;
import com.workintech.twitter_api.entitiy.User;
import com.workintech.twitter_api.exceptions.CommentNotFoundException;
import com.workintech.twitter_api.exceptions.UserNotFoundException;
import com.workintech.twitter_api.mapper.CommentMapper;
import com.workintech.twitter_api.repository.CommentRepository;
import com.workintech.twitter_api.repository.TweetRepository;
import com.workintech.twitter_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService{

    private CommentRepository commentRepository;
    private UserRepository userRepository;
    private TweetRepository tweetRepository;
    private CommentMapper commentMapper;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, UserRepository userRepository, TweetRepository tweetRepository, CommentMapper commentMapper){
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.tweetRepository = tweetRepository;
        this.commentMapper = commentMapper;
    }

    @Override
    public List<CommentResponse> findByUserId(long userId) {
        return commentRepository.findByUserId(userId).stream().map(c -> commentMapper.toResponse(c)).toList();
    }

    @Override
    public CommentResponse findById(long id) {
        Optional<Comment> commentOptional = commentRepository.findById(id);
        if(commentOptional.isPresent()){
            return commentMapper.toResponse(commentOptional.get());
        } else{
            throw new CommentNotFoundException("Yorum bulunamadı.");
        }
    }

    @Override
    public CommentResponse create(CommentRequest commentRequest) {
        Comment comment = commentMapper.toEntity(commentRequest);
        Optional<User> userOptional = userRepository.findById(commentRequest.userId());
        Optional<Tweet> tweetOptional = tweetRepository.findById(commentRequest.tweetId());
        if(userOptional.isPresent() && tweetOptional.isPresent()){
            comment.setUser(userOptional.get());
            comment.setText(commentRequest.text());
            comment.setTweet(tweetOptional.get());
            commentRepository.save(comment);
            return commentMapper.toResponse(comment);
        } else {
            throw new UserNotFoundException("Kullanıcı bulunamadı veya Tweet bulunamadı");
        }
    }

    @Override
    public CommentResponse update(long id, CommentRequest commentRequest) {
        Comment commentToUpdate = commentRepository.findById(id).orElseThrow(() -> new CommentNotFoundException("Yorum bulunamadı."));
        commentToUpdate = commentMapper.updateEntity(commentToUpdate, commentRequest);
        commentRepository.save(commentToUpdate);
        return commentMapper.toResponse(commentToUpdate);
    }

    @Override
    public void delete(long id) {
        commentRepository.deleteById(id);
    }
}
