package com.codebase.backend.post.service;

import com.codebase.backend.post.dto.PostDTO;
import com.codebase.backend.post.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void save(PostDTO post) { postRepository.save(post); }
    public List<PostDTO> findAll() { return postRepository.findAll(); }
    public PostDTO findById(Long id) { return postRepository.findById(id); }
    public void update(PostDTO post) { postRepository.update(post); }
    public void delete(Long id) { postRepository.delete(id); }
}
