package com.kong.rabs.exception;

import java.util.Objects;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {
    @ExceptionHandler(RabsException.class)
    public ResponseEntity<ErrorResponse> rabsException(RabsException e) {
        ErrorResponse errorResponse;
        if (Objects.isNull(e.getErrorType())) {
            errorResponse = ErrorResponse.of(ErrorType.UNKNOWN);
        } else {
            errorResponse = ErrorResponse.of(e.getErrorType());
        }

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
