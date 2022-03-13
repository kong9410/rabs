package com.kong.rabs.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorType {
    UNKNOWN("알 수 없는 에러가 발생했습니다.", HttpStatus.BAD_REQUEST),
    USER_ALREADY_EXISTS("이미 존재하는 유저입니다.", HttpStatus.OK);

    private final String errorMessage;
    private final HttpStatus httpStatus;
}
