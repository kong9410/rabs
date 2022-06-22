package com.kong.rabs.auth.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Authority {
    @Id
    @Column(name = "authority_name", length=15)
    private String authorityName;

    public Authority(String authorityName) {
        this.authorityName = authorityName;
    }
}
