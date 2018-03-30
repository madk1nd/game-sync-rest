package ru.goodgame.dto;

import javax.annotation.Nonnull;

public class ResponseDTO {
    @Nonnull private final Result result;
    @Nonnull private final String message;

    public ResponseDTO(@Nonnull Result result, @Nonnull String message) {
        this.result = result;
        this.message = message;
    }

    @Nonnull
    public Result getResult() {
        return result;
    }

    @Nonnull
    public String getMessage() {
        return message;
    }
}
