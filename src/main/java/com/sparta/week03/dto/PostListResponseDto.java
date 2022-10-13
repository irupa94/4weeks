package com.sparta.week03.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class PostListResponseDto {
    private String title;

    private Long id;
    private String author;  // username
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}