package com.kong.rabs.user.controller;

import com.kong.rabs.user.model.UserParam;
import com.kong.rabs.user.service.UserService;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class RegisterController {
    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public boolean registerUser(@RequestBody @Valid UserParam userParam) {
        return userService.addUser(userParam);
    }
}
