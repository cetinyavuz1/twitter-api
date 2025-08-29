package com.workintech.twitter_api.mapper;

import com.workintech.twitter_api.dto.CommentRequest;
import com.workintech.twitter_api.dto.CommentResponse;
import com.workintech.twitter_api.entitiy.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    private UserMapper userMapper;
    private TweetMapper tweetMapper;

    @Autowired
    public CommentMapper(UserMapper userMapper, TweetMapper tweetMapper){
        this.userMapper = userMapper;
        this.tweetMapper = tweetMapper;
    }

    public Comment toEntity(CommentRequest commentRequest){
        Comment comment = new Comment();
        comment.setText(commentRequest.text());
        return comment;
    }

    public CommentResponse toResponse(Comment comment){
        return new CommentResponse(comment.getUser().getUserName(), comment.getText(), comment.getTweet().getId());
    }

    public Comment updateEntity(Comment updatedComment, CommentRequest commentRequest){
        if(commentRequest.text() != null){
            updatedComment.setText(commentRequest.text());
        }
        return updatedComment;
    }

}
