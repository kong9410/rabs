package com.kong.rabs.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private ErrorType errorType;

    public static ErrorResponse of(ErrorType errorType) {
        return new ErrorResponse(errorType);
    }
}
