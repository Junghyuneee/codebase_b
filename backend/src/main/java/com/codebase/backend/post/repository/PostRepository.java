package com.codebase.backend.post.repository;

import com.codebase.backend.post.dto.PostDTO;
import java.util.List;

public interface PostRepository {
    void save(PostDTO post);
    List<PostDTO> findAll();
    PostDTO findById(Long id);
    void update(PostDTO post);
    void delete(Long id);
}
