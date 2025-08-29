package com.workintech.twitter_api.exceptions;

import org.springframework.http.HttpStatus;

public class AlreadyLikedException extends TwitterException{
    public AlreadyLikedException(String message) {
        super(message, HttpStatus.CONFLICT);
    }
}
