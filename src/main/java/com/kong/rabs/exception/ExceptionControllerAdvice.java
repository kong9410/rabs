package com.kong.rabs.exception;

import java.util.Objects;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

        return new ResponseEntity<>(errorResponse, errorResponse.getErrorType().getHttpStatus());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorResponse> usernameNotFoundException() {
        ErrorResponse errorResponse = ErrorResponse.of(ErrorType.USERNAME_NOT_FOUND);

        return new ResponseEntity<>(errorResponse, errorResponse.getErrorType().getHttpStatus());
    }
}
