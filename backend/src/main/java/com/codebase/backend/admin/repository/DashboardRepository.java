package com.codebase.backend.admin.repository;

import com.codebase.backend.admin.dto.NewMemberResponse;
import com.codebase.backend.admin.dto.Visitor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class DashboardRepository {

    @Autowired
    private SqlSessionTemplate sql;

    // 방문자 수
    public void saveVisitor(Visitor visitor) {
        this.sql.insert("Visitor.save", visitor);
    }

    public boolean isIpRecordedRecently(Visitor visitor) {
        return this.sql.selectOne("Visitor.checkDuplicateIp", visitor);
    }

    public List<Map<String, Object>> getWeeklyVisitorCount() {
        return this.sql.selectList("Visitor.getWeeklyVisitorCount");
    }

    // 신규 회원 수
    public List<Map<String, Object>> getWeeklyNewMemberCount() {
        return this.sql.selectList("Member.getWeeklyNewMemberCount");
    }

}
