package com.codebase.backend.comment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.codebase.backend.comment.dto.CommentDto;

@Mapper
public interface CommentMapper {

    // 댓글 작성
    void insertComment(CommentDto comment);

    // 특정 게시글의 댓글 목록 조회
    List<CommentDto> findCommentsByPostId(@Param("postId") long postId);

    // 댓글 수정
    void updateComment(CommentDto comment);

    // 댓글 삭제
    void deleteComment(@Param("id") long id);
}