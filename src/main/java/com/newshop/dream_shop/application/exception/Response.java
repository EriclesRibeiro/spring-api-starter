package com.newshop.dream_shop.application.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {

    private final T data;
    private final ResponseError error;

    private Response(T data, ResponseError error) {
        this.data = data;
        this.error = error;
    }

    public static <T> Response<T> success(T data) {
        return new Response<>(data, null);
    }

    public static <T> Response<T> error(ResponseError responseError) {
        return new Response<>(null, responseError);
    }

}
