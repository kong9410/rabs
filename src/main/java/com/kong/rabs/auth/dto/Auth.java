package com.kong.rabs.auth.dto;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class Auth {
    @Getter
    public static class Request {
        @NotBlank
        private String username;
        @NotBlank
        private String password;
    }

    @RequiredArgsConstructor
    @Getter
    public static class Response {
        private final String token;
    }
}
