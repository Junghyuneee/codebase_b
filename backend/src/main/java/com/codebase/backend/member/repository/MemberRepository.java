package com.codebase.backend.member.repository;

import com.codebase.backend.member.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final SqlSessionTemplate sql;

    public void save(MemberDTO memberDTO) {
        sql.insert("Member.save", memberDTO);
    }
}
