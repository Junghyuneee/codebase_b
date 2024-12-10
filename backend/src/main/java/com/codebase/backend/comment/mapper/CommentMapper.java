package com.codebase.backend.comment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.codebase.backend.comment.dto.CommentDTO;

// src/main/java/com/codebase/backend/comment/mapper/CommentMapper.java
@Mapper
public interface CommentMapper {
    List<CommentDTO> getCommentsByPostId(Long postId);
    void insertComment(CommentDTO comment);
    void updateComment(CommentDTO comment);
    void deleteComment(Long id);
}