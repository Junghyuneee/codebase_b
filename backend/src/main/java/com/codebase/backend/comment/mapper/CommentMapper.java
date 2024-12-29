package com.codebase.backend.comment.mapper;

import com.codebase.backend.comment.dto.CommentDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    void insertComment(CommentDto commentDto);
    List<CommentDto> findCommentsByPostId(Long postId);
    void deleteComment(Long id);
    
    // 댓글 수정 메서드 추가
    void updateComment(CommentDto commentDto);
}
