package com.codebase.backend.admin.repository;

import com.codebase.backend.admin.dto.Visitor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class VisitorRepository {

    @Autowired
    private SqlSessionTemplate sql;

    public void saveVisitor(Visitor visitor) {
        this.sql.insert("Visitor.save", visitor);
    }

    public boolean isIpRecordedRecently(Visitor visitor) {
        return this.sql.selectOne("Visitor.checkDuplicateIp", visitor);
    }

    public List<Map<String, Object>> getWeeklyVisitorCount() {
        return this.sql.selectList("Visitor.getWeeklyVisitorCount");
    }

}
