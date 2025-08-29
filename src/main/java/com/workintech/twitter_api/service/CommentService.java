package com.workintech.twitter_api.service;

import com.workintech.twitter_api.dto.CommentRequest;
import com.workintech.twitter_api.dto.CommentResponse;

import java.util.List;

public interface CommentService {
    List<CommentResponse> findByUserId(long userId);
    CommentResponse findById(long id);
    CommentResponse create(CommentRequest commentRequest);
    CommentResponse update(long id, CommentRequest commentRequest);
    void delete(long id);
}
