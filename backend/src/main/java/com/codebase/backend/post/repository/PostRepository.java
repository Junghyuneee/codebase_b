package com.codebase.backend.post.repository;

import com.codebase.backend.post.dto.PostDTO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepository {

    private final SqlSessionTemplate sql;

    public void save(PostDTO postDTO) {
        sql.insert("Post.save", postDTO);
    }

    public List<PostDTO> findAll() {
        return sql.selectList("Post.findAll");
    }

    public PostDTO findById(Long id) {
        return sql.selectOne("Post.findById", id);
    }

    public void update(PostDTO postDTO) {
        sql.update("Post.update", postDTO);
    }

    public void delete(Long id) {
        sql.delete("Post.delete", id);
    }
}
