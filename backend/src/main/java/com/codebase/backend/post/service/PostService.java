package com.codebase.backend.post.service;

import com.codebase.backend.post.dto.PostDTO;
import com.codebase.backend.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public void createPost(PostDTO postDTO) {
        postRepository.save(postDTO);
    }

    public List<PostDTO> getAllPosts() {
        return postRepository.findAll();
    }

    public PostDTO getPostById(Long id) {
        return postRepository.findById(id);
    }

    public void updatePost(PostDTO postDTO) {
        postRepository.update(postDTO);
    }

    public void deletePost(Long id) {
        postRepository.delete(id);
    }
}
