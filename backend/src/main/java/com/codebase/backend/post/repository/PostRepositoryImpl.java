package com.codebase.backend.post.repository;

import com.codebase.backend.post.dto.PostDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostRepositoryImpl implements PostRepository {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public void insertPost(PostDTO postDTO) {
        sqlSession.insert("com.codebase.backend.post.repository.PostMapper.insertPost", postDTO);
    }

    @Override
    public List<PostDTO> selectAllPosts() {
        return sqlSession.selectList("com.codebase.backend.post.repository.PostMapper.selectAllPosts");
    }

    @Override
    public PostDTO selectPostById(Long id) {
        return sqlSession.selectOne("com.codebase.backend.post.repository.PostMapper.selectPostById", id);
    }

    @Override
    public void updatePost(PostDTO postDTO) {
        sqlSession.update("com.codebase.backend.post.repository.PostMapper.updatePost", postDTO);
    }

    @Override
    public void deletePost(Long id) {
        sqlSession.delete("com.codebase.backend.post.repository.PostMapper.deletePost", id);
    }
}
