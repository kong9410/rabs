package com.kong.rabs.exception;

import lombok.Getter;

@Getter
public class RabsException extends RuntimeException {
    private final ErrorType errorType;

    public RabsException(ErrorType errorType) {
        this.errorType = errorType;
    }
}
