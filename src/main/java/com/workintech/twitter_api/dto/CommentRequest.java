package com.workintech.twitter_api.dto;

public record CommentRequest(String text, Long userId, Long tweetId) {
}
