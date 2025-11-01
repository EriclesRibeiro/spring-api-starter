package com.newshop.dream_shop.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * @description: Not found error handler
     * @since: 01/11/2025
    */

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Response<?>> handleNotFound(NotFoundException exception, WebRequest request) {
        ResponseError error = new ResponseError(
                exception.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                request.getDescription(false));

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Response.error(error));
    }

    /**
    * @description: Generic error handler
    * @since: 01/11/2025
    */

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response<?>> handleGeneric(Exception exception, WebRequest request) {
        ResponseError error = new ResponseError(
                exception.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                request.getDescription(false));

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Response.error(error));

    }

}
