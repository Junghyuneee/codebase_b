package com.codebase.backend.admin.repository;

import com.codebase.backend.admin.dto.Report;
import com.codebase.backend.member.dto.Member;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class MemberAndChatRepository {

    @Autowired
    private SqlSessionTemplate sql;

    public List<Member> getAllMembers(Map<String, Object> params) {
        return this.sql.selectList("Member.selectAllMembers", params);
    }

    public int countAllMembers() {
        return this.sql.selectOne("Member.countAllMembers");
    }

}
