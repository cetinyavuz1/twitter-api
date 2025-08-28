package com.workintech.twitter_api.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class TwitterException extends RuntimeException {
  private HttpStatus httpStatus;

  public TwitterException(String message, HttpStatus httpStatus){
    super(message);
    this.httpStatus = httpStatus;
  }

  public HttpStatus getHttpStatus(){
    return httpStatus;
  }
}