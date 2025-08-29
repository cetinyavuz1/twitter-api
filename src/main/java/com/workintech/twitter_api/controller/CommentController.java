package com.workintech.twitter_api.controller;

import com.workintech.twitter_api.dto.CommentRequest;
import com.workintech.twitter_api.dto.CommentResponse;
import com.workintech.twitter_api.service.CommentService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }

    @GetMapping("/user/{userId}")
    public List<CommentResponse> findByUserId(@PathVariable long userId){
        return commentService.findByUserId(userId);
    }

    @GetMapping("/{id}")
    public CommentResponse findById(@PathVariable long id){
        return commentService.findById(id);
    }

    @PostMapping
    public CommentResponse create(@Validated @RequestBody CommentRequest commentRequest){
        return commentService.create(commentRequest);
    }

    @PutMapping("/{id}")
    public CommentResponse update(@PathVariable long id, @Validated @RequestBody CommentRequest commentRequest){
        return commentService.update(id, commentRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        commentService.delete(id);
    }
}
