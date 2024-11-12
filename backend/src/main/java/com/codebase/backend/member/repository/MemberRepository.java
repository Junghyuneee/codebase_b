package com.codebase.backend.member.repository;

import com.codebase.backend.member.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final SqlSessionTemplate sql;

    public MemberDTO save(MemberDTO memberDTO) {
        sql.insert("Member.save", memberDTO);
        return memberDTO;
    }

    public MemberDTO update(MemberDTO memberDTO) {
        sql.update("Member.update", memberDTO);
        return memberDTO;
    }

    public MemberDTO findByEmail(String email) { return sql.selectOne("Member.findByEmail", email); }
}
