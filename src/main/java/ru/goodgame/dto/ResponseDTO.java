package ru.goodgame.dto;

import lombok.EqualsAndHashCode;

import javax.annotation.Nonnull;

@EqualsAndHashCode
public class ResponseDTO {
    @Nonnull private final Result result;
    @Nonnull private final String message;
    @Nonnull private final Object data;

    public ResponseDTO(@Nonnull Result result, @Nonnull String message, @Nonnull Object data) {
        this.result = result;
        this.message = message;
        this.data = data;
    }

    @Nonnull
    public Result getResult() {
        return result;
    }

    @Nonnull
    public String getMessage() {
        return message;
    }

    @Nonnull
    public Object getData() {
        return data;
    }

    @Nonnull
    public static ResponseDTO goodResponse(@Nonnull String message, @Nonnull Object data) {
        return new ResponseDTO(Result.OK, message, data);
    }

    @Nonnull
    public static ResponseDTO badResponse(@Nonnull String  message) {
        return new ResponseDTO(Result.FAIL, message, "");
    }
}
