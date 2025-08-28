package com.workintech.twitter_api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<TwitterErrorResponse> handleException(TwitterException twitterException){
        TwitterErrorResponse response = new TwitterErrorResponse(
                twitterException.getMessage(),
                twitterException.getHttpStatus().value()
        );
        return new ResponseEntity<>(response, twitterException.getHttpStatus());
    }

    @ExceptionHandler
    public ResponseEntity<TwitterErrorResponse>  handleException(Exception exception){
        TwitterErrorResponse response = new TwitterErrorResponse(
                exception.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
