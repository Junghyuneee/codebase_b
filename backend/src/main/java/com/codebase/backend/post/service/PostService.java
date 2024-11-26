package com.codebase.backend.post.service;

import java.util.List;

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
    public PostDTO selectPostById(Long id) {
        return postMapper.selectPostById(id);
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
        postMapper.updatePost(id, topic, title, content); // DB에서 업데이트 수행
        return postMapper.selectPostById(id); // 수정된 게시글을 DB에서 다시 조회
    }
}
