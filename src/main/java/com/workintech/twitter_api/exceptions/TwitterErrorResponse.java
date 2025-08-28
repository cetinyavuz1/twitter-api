package com.workintech.twitter_api.exceptions;


public class TwitterErrorResponse {
    private String message;
    private int status;

    public TwitterErrorResponse(String message, int status) {
        this.message = message;
        this.status = status;
    }
}
