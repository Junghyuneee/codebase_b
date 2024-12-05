package com.codebase.backend.post.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public PostDTO selectPostById(Long id) {
        PostDTO post = postMapper.selectPostById(id);
        if (post == null) {
            throw new RuntimeException("게시물이 존재하지 않습니다."); // 예외 처리
        }
        return post;
    }

    // 게시글 조회수 증가
    public void increaseViews(Long id) {
        postMapper.increaseViews(id);
    }

    // 게시글 삭제
    public void deletePost(Long id) {
        postMapper.deletePost(id);
    }

    // 게시글 수정
    public PostDTO updatePost(Long id, String topic, String title, String content) {
        postMapper.updatePost(id, topic, title, content);
        return postMapper.selectPostById(id); // 수정된 게시글을 DB에서 다시 조회
    }

    // 좋아요
    public PostDTO likePost(Long id) {
        try {
            postMapper.increaseLikeCount(id); // 좋아요 수 증가
            return postMapper.selectPostById(id); // 수정된 게시물 반환
        } catch (Exception e) {
            // 로그 추가
            System.err.println("좋아요 처리 중 오류 발생: " + e.getMessage());
            throw new RuntimeException("좋아요 처리 실패: " + e.getMessage());
        }
    }


    // 싫어요
    public PostDTO dislikePost(Long id) {
        postMapper.increaseDislikeCount(id); // 싫어요 수 증가
        return postMapper.selectPostById(id); // 수정된 게시물 반환
    }
}