package com.workintech.twitter_api.dto;

import org.antlr.v4.runtime.misc.NotNull;

public record TweetRequest(
        @NotNull Long userId,
        @NotNull String text) {
}
