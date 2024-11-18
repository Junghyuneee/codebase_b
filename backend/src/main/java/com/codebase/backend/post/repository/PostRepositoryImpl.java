package com.codebase.backend.post.repository;

import com.codebase.backend.post.dto.PostDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostRepositoryImpl implements PostRepository {

    private final SqlSession sqlSession;

    @Autowired
    public PostRepositoryImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public void save(PostDTO post) {
        sqlSession.insert("PostMapper.save", post);
    }

    @Override
    public List<PostDTO> findAll() {
        return sqlSession.selectList("PostMapper.findAll");
    }

    @Override
    public PostDTO findById(Long id) {
        return sqlSession.selectOne("PostMapper.findById", id);
    }

    @Override
    public void update(PostDTO post) {
        sqlSession.update("PostMapper.update", post);
    }

    @Override
    public void delete(Long id) {
        sqlSession.delete("PostMapper.delete", id);
    }
}
