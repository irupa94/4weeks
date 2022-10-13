package com.sparta.week03.service;

import com.sparta.week03.dto.CommentRequestDto;
import com.sparta.week03.dto.CommentResponseDto;
import com.sparta.week03.entity.Comment;
import com.sparta.week03.entity.User;
import com.sparta.week03.repository.CommentRepository;
import com.sparta.week03.repository.PostRepository;
import com.sparta.week03.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    private User getUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow( () -> new UsernameNotFoundException("아이디를 찾을 수 없습니다."));
        return user;
    }

    public void postCheck(CommentRequestDto commentRequestDto) {
        postRepository.findById( commentRequestDto.getPostId() )
                .orElseThrow( () -> new IllegalArgumentException("댓글이 존재하지 않습니다."));
    }

    //댓글 쓰기
    @Transactional
    public CommentResponseDto createComment(CommentRequestDto commentRequestDto, String username) {
        User user = getUser(username);
        postCheck(commentRequestDto);

        Comment comment = new Comment(commentRequestDto, user);
        commentRepository.save(comment);

        return new CommentResponseDto(comment);

    }

    //댓글 수정
    @Transactional
    public CommentResponseDto updateComment(Long id, CommentRequestDto commentRequestDto, String username) {
        User user = getUser(username);
        postCheck(commentRequestDto);

        Comment comment = commentRepository.findById(id)
                .orElseThrow( () -> new IllegalArgumentException("댓글이 없습니다."));

        if(!user.getUsername().equals(comment.getUser().getUsername())){
            throw new IllegalArgumentException("작성자만 수정할 수 있습니다.");
        }

        comment.update(commentRequestDto);
        commentRepository.save(comment);
        return new CommentResponseDto(comment);
    }

    //댓글 삭제
    @Transactional
    public String deleteComment(Long id, String username) {
        User user = getUser(username);

        Comment comment = commentRepository.findById(id)
                .orElseThrow( () -> new IllegalArgumentException("댓글이 없습니다."));

        if(!user.getUsername().equals(comment.getUser().getUsername())){
            throw new IllegalArgumentException("작성자만 삭제할 수 있습니다.");
        }

        commentRepository.deleteById(id);
        return "댓글이 삭제되었습니다";
    }

    //댓글 전체목록 보기
    public List<CommentResponseDto> getCommentAllOfPost(Long id) {
        List<Comment> list = commentRepository.findAllByPostId(id);
        List<CommentResponseDto> clist = new ArrayList<>();
        for (Comment c : list) {
            //CommentResponseDto commentResponseDto = new CommentResponseDto(c);
            clist.add(new CommentResponseDto(c));
        }
        return clist;
    }
}