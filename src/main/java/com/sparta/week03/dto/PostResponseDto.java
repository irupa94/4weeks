package com.sparta.week03.dto;

import com.sparta.week03.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
public class PostResponseDto {
    private String title;
    private String contents;

    private Long id;
    private String author;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public PostResponseDto (Post post){
        this.title = post.getTitle();
        this.contents = post.getContents();

        this.id = post.getId();
        this.author = post.getUser().getUsername();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
    }


}