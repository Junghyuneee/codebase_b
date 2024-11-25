package com.codebase.backend.admin.repository;

import com.codebase.backend.admin.dto.Report;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReportRepository {

    @Autowired
    private SqlSessionTemplate sql;

    public void saveReport(Report report) {
        this.sql.insert("Report.save", report);
    }

    public List<Report> getAllReport() {
        return this.sql.selectList("Report.selectAllReport");
    }

}
