package com.codebase.backend.post.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.codebase.backend.post.dto.PostDTO;
import com.codebase.backend.post.mapper.PostMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostMapper postMapper;

    // 게시글 등록
    public void createPost(PostDTO post) {
        postMapper.insertPost(post);
    }

    // 모든 게시글 목록 조회
    public List<PostDTO> selectAllPosts() {
        return postMapper.selectAllPosts();
    }

    // 특정 게시글 조회
    public PostDTO selectPostById(int id) {
        PostDTO post = postMapper.selectPostById(id);
        if (post == null) {
            throw new RuntimeException("게시물이 존재하지 않습니다."); // 예외 처리
        }
        return post;
    }

    // 게시글 조회수 증가
    public void increaseViews(int id) {
        postMapper.increaseViews(id);
    }

    // 게시글 삭제
    public boolean deletePost(int id) {
        boolean isDeleted = postMapper.deletePost(id); // 삭제 성공 여부 반환
        if (!isDeleted) {
            throw new RuntimeException("게시물 삭제 실패: 게시물이 존재하지 않거나 이미 삭제되었습니다.");
        }
        return isDeleted;
    }

    // 게시글 수정
    public PostDTO updatePost(int id, String topic, String title, String content, List<String> tags) {
        PostDTO existingPost = selectPostById(id); // 게시글 존재 여부 확인
        postMapper.updatePost(id, topic, title, content, tags); // tags 추가
        return postMapper.selectPostById(id); // 수정된 게시글을 DB에서 다시 조회
    }

    // 게시글 좋아요
    public void updateLikes(int id, String userId) {
        if (postMapper.hasUserLiked(id, userId)) {
            postMapper.updateLikes(id, -1);
            postMapper.removeUserLike(id, userId);
        } else {
            postMapper.updateLikes(id, 1);
            postMapper.addUserLike(id, userId);
            if (postMapper.hasUserDisliked(id, userId)) {
                postMapper.updateDislikes(id, -1); // 싫어요 취소
                postMapper.removeUserDislike(id, userId);
            }
        }
    }

    // 게시글 싫어요
    public void updateDislikes(int id, String userId) {
        if (postMapper.hasUserDisliked(id, userId)) {
            postMapper.updateDislikes(id, -1);
            postMapper.removeUserDislike(id, userId);
        } else {
            postMapper.updateDislikes(id, 1);
            postMapper.addUserDislike(id, userId);
            if (postMapper.hasUserLiked(id, userId)) {
                postMapper.updateLikes(id, -1); // 좋아요 취소
                postMapper.removeUserLike(id, userId);
            }
        }
    }

    // 현재 사용자의 좋아요/싫어요 상태 조회
    public Map<String, Boolean> getLikeStatus(int postId, String userId) {
        Map<String, Boolean> likeStatus = new HashMap<>();
        likeStatus.put("liked", postMapper.hasUserLiked(postId, userId));
        likeStatus.put("disliked", postMapper.hasUserDisliked(postId, userId));
        return likeStatus;
    }

    //	프로필용 게시글
    public List<PostDTO> getPostByMemberName(String memberName) {
        return postMapper.selectPostByAuthor(memberName);
    }
}
