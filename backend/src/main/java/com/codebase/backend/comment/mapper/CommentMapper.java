package com.codebase.backend.comment.mapper;

import com.codebase.backend.comment.dto.CommentDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    void insertComment(CommentDto commentDto);
    List<CommentDto> findCommentsByPostId(Long postId);
    List<CommentDto> findAllComments();
    void deleteComment(Long id);
    void updateComment(CommentDto commentDto);
    CommentDto findCommentById(Long id);
}
