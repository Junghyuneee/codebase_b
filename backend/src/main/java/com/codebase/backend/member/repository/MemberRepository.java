package com.codebase.backend.member.repository;

import com.codebase.backend.member.dto.Member;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final SqlSessionTemplate sql;

    public Member save(Member member) {
        sql.insert("Member.save", member);
        return member;
    }

    public Member update(Member member) {
        sql.update("Member.update", member);
        return member;
    }

    public Member findByEmail(String email) { return sql.selectOne("Member.findByEmail", email); }

    public List<Member> searchByName(String name) { return sql.selectList("Member.searchByName", name); }

    public Member findById(Integer id) {
        return sql.selectOne("Member.findById", id);
    }

    public Member findByName(String name) { return sql.selectOne("Member.findByName", name); }
}
