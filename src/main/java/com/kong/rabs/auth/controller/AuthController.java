package com.kong.rabs.auth.controller;

import com.kong.rabs.auth.dto.Auth;
import com.kong.rabs.auth.dto.Auth.Response;
import com.kong.rabs.auth.dto.SignUpRequest;
import com.kong.rabs.auth.service.AuthService;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signin")
    public ResponseEntity<Response> signIn(@RequestBody @Valid Auth.Request authDtoRequest) {
        return authService.signIn(authDtoRequest);
    }

    @PostMapping("/signup")
    public void signUp(@RequestBody @Valid SignUpRequest signUpRequest) {
        authService.signUp(signUpRequest);
    }
}
