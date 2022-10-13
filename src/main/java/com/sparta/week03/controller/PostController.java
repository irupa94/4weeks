package com.sparta.week03.controller;

import com.sparta.week03.dto.PostListResponseDto;
import com.sparta.week03.dto.PostRequestDto;
import com.sparta.week03.dto.PostResponseDto;
import com.sparta.week03.entity.Post;
import com.sparta.week03.repository.PostRepository;
import com.sparta.week03.security.UserDetailImpl;
import com.sparta.week03.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    // 글 작성
    @PostMapping("/api/auth/posts")
    public PostResponseDto createPost(@RequestBody PostRequestDto postRequestDto, @AuthenticationPrincipal UserDetailImpl userDetailImpl) {
        return postService.createPost(postRequestDto, userDetailImpl.getUsername());
    }

    // 글 전체보기
    @GetMapping("/api/posts")
    public List<PostListResponseDto> getPostAll() {
        return postService.getPostAll();
    }

    // 글 상세보기
    @GetMapping("/api/posts/{id}")
    public PostResponseDto getPostDetail(@PathVariable Long id) {
        return postService.getPostDetail(id);
    }

    // 글 수정
    @PutMapping("/api/auth/posts/{id}")
    public PostResponseDto updataPost(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto, @AuthenticationPrincipal UserDetailImpl userDetailImpl) {
        return postService.updatePost(id, postRequestDto, userDetailImpl.getUsername());
    }

    // 글 삭제
    @DeleteMapping("/api/auth/posts/{id}")
    public String deletePost(@PathVariable Long id, @AuthenticationPrincipal UserDetailImpl userDetailImpl) {
        return postService.deletePost(id, userDetailImpl.getUsername());
    }
}
