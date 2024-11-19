package com.codebase.backend.post.repository;

import com.codebase.backend.post.dto.PostDTO;

import java.util.List;

public interface PostRepository {
    void insertPost(PostDTO postDTO);
    List<PostDTO> selectAllPosts();
    PostDTO selectPostById(Long id);
    void updatePost(PostDTO postDTO);
    void deletePost(Long id);
}
