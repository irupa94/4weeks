package com.sparta.week03.dto;

import com.sparta.week03.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
public class CommentRequestDto {
    private Long postId;
    private String comment;
    private User user;
}