package com.kong.rabs.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 25)
    @NotBlank
    private String username;

    @Column(nullable = false, length = 100)
    @NotBlank
    private String password;

    private String role;

    public Users(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
