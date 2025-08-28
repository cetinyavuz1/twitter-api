package com.workintech.twitter_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.antlr.v4.runtime.misc.NotNull;

public record UserRequest(@NotNull
                          @JsonProperty("userName")
                          String userName,
                          String email,
                          String password) {
}
