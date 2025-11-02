package com.newshop.dream_shop.application.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ResponseError {

    private String message;
    private int status;
    private LocalDateTime timestamp;
    private String path;

    public ResponseError(String message, int status, String path) {
        this.message = message;
        this.path = path;
        this.status = status;
        this.timestamp = LocalDateTime.now();
    }

}
