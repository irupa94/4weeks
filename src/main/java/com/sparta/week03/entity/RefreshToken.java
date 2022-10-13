package com.sparta.week03.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity(name = "refresh_token")
public class RefreshToken {
    @Id
    @Column(name = "refreshtoken_key")
    private String key;

    @Column(name = "refreshtoken_value")
    private String value;

    @Builder
    public RefreshToken(String key, String value) {
        this.key = key;
        this.value = value;
    }
}