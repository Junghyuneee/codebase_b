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
    PostDTO selectPostById(@Param("id") int id);
    
    // 게시글 조회수 증가
    void increaseViews(@Param("id") int id);
    
    // 게시글 수정
    void updatePost(@Param("id") int id,
                    @Param("topic") String topic, 
                    @Param("title") String title, 
                    @Param("content") String content,
                    @Param("tags") List<String> tags); // 태그 업데이트 추가
    
    // 게시글 삭제 (성공 여부 반환)
    boolean deletePost(@Param("id") int id);
    
    // 게시글 좋아요 싫어요
    void updateLikes(@Param("id") int id, @Param("increment") int increment);
    void updateDislikes(@Param("id") int id, @Param("increment") int increment);

    // 사용자 좋아요 상태 확인
    boolean hasUserLiked(@Param("postId") int postId, @Param("userId") String userId);

    // 사용자 싫어요 상태 확인
    boolean hasUserDisliked(@Param("postId") int postId, @Param("userId") String userId);

    // 사용자 좋아요 추가
    void addUserLike(@Param("postId") int postId, @Param("userId") String userId);

    // 사용자 싫어요 추가
    void addUserDislike(@Param("postId") int postId, @Param("userId") String userId);

    // 사용자 좋아요 제거
    void removeUserLike(@Param("postId") int postId, @Param("userId") String userId);

    // 사용자 싫어요 제거
    void removeUserDislike(@Param("postId") int postId, @Param("userId") String userId);

    // 인기 게시글 조회
    List<PopularResponse> findPopularPosts();


//    id로 게시글찾기
    List<PostDTO> selectPostByAuthor(@Param("author") String author);
}
