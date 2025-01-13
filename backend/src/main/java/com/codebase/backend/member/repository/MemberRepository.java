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

    public void socialCreate(Member member) {
        sql.update("Member.socialCreate", member);
    }

    public Member findByEmail(String email) {
        return sql.selectOne("Member.findByEmail", email);
    }

    public List<Member> searchByName(String name) {
        return sql.selectList("Member.searchByName", name);
    }

    public Member findById(Integer id) {
        return sql.selectOne("Member.findById", id);
    }

    public Member findByName(String name) {
        return sql.selectOne("Member.findByName", name);
    }

    public List<Member> findByMemberNames(List<String> memberNames) {
        return sql.selectList("Member.findByMemberNames", memberNames);
    }

    public void update(Member member) {
        sql.update("Member.update", member);
    }

    public void updatePassword(Member member) {
        sql.update("Member.updatePassword", member);
    }

    public boolean removeMemberByMail(String mail) {
        int result = sql.delete("Member.removeMemberByMail", mail);
        return result > 0; // 삭제된 행이 1개 이상이면 true, 아니면 false
    }
}
