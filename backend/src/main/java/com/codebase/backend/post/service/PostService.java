package com.codebase.backend.post.service;

import com.codebase.backend.post.dto.PostDTO;
import com.codebase.backend.post.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public void createPost(PostDTO postDTO) {
        postRepository.insertPost(postDTO);
    }

    public List<PostDTO> getAllPosts() {
        return postRepository.selectAllPosts();
    }

    public PostDTO getPostById(Long id) {
        return postRepository.selectPostById(id);
    }

    public PostDTO updatePost(PostDTO postDTO) {
        postRepository.updatePost(postDTO);
        return postDTO; // 수정된 게시물 반환
    }

    public boolean deletePost(Long id) {
        postRepository.deletePost(id);
        return true; // 삭제 성공 시 true 반환
    }
}
