package com.kong.rabs.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 25)
    @NotBlank
    private String account;

    @Column(nullable = false, length = 25)
    @NotBlank
    private String password;

    @Builder
    public User(String account, String password) {
        this.account = account;
        this.password = password;
    }
}
