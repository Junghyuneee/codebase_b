package com.codebase.backend.post.mapper;

import java.util.List;

import com.codebase.backend.admin.dto.PopularResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.codebase.backend.post.dto.PostDTO;

@Mapper
public interface PostMapper {

    // 게시글 저장
    void insertPost(PostDTO post);

    // 모든 게시글 조회
    List<PostDTO> selectAllPosts();

    // 특정 게시글 조회
    PostDTO selectPostById(@Param("id") Long id);
    
    // 게시글 조회수 증가
    void increaseViews(@Param("id") Long id);
    
    // 게시글 수정
    void updatePost(@Param("id") Long id, @Param("topic") String topic, @Param("title") String title, @Param("content") String content);
    
    // 게시글 삭제 (성공 여부 반환)
    boolean deletePost(@Param("id") Long id);
    
    // 좋아요 수 증가
    void increaseLikeCount(@Param("id") Long id);
    
    // 싫어요 수 증가
    void increaseDislikeCount(@Param("id") Long id);

    // 인기 게시글 조회
    List<PopularResponse> findPopularPosts();
}
