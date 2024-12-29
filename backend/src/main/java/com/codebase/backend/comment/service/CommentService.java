package com.codebase.backend.comment.service;

import com.codebase.backend.comment.dto.CommentDto;
import com.codebase.backend.comment.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private final CommentMapper commentMapper;

    @Autowired
    public CommentService(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    public void addComment(CommentDto commentDto) {
        commentMapper.insertComment(commentDto);
    }

    public List<CommentDto> getCommentsByPostId(Long postId) {
        return commentMapper.findCommentsByPostId(postId);
    }

    public void deleteComment(Long id) {
        commentMapper.deleteComment(id);
    }

    // 댓글 수정 메서드 추가
    public void updateComment(CommentDto commentDto) {
        commentMapper.updateComment(commentDto);
    }
}
