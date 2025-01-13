package com.codebase.backend.comment.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.codebase.backend.comment.dto.CommentDto;
import com.codebase.backend.comment.mapper.CommentMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentMapper commentMapper;

    // 댓글 작성
    public CommentDto createComment(CommentDto comment) {
        commentMapper.insertComment(comment);
        return comment;
    }

    // 특정 게시글의 댓글 목록 조회
    public List<CommentDto> getCommentsByPostId(long postId) {
        return commentMapper.findCommentsByPostId(postId);
    }


    public List<CommentDto> getAllComments() {
        return commentMapper.findAllComments();



    // 댓글 수정
    public CommentDto updateComment(long id, CommentDto comment) {
        comment.setId(id);
        commentMapper.updateComment(comment);

    }

    // 댓글 삭제
    public void deleteComment(long id) {
        commentMapper.deleteComment(id);
    }

    public void updateComment(CommentDto commentDto) {
        commentMapper.updateComment(commentDto);
    }

    public CommentDto getCommentById(Long id) {
        return commentMapper.findCommentById(id);
    }
}

}

