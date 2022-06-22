package com.kong.rabs.auth.dto;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
public class SignUpRequest {
    @NotBlank
    @Length(min = 4, max = 25)
    private String username;
    @NotBlank
    @Length(min = 6, max = 25)
    private String password;
}
