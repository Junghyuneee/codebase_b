/*
김은지
2024 11 25
*/
package com.codebase.backend.admin.repository;

import com.codebase.backend.admin.dto.Report;
import com.codebase.backend.admin.dto.ReportDetail;
import com.codebase.backend.admin.dto.ReportRequest;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReportRepository {

    @Autowired
    private SqlSessionTemplate sql;

    public void saveReport(ReportRequest reportRequest) { // 신고 테이블 추가 or 카운트 업데이트
        this.sql.insert("Report.insertOrUpdateReport", reportRequest);
    }

    public void saveReportDetail(ReportRequest reportRequest) { // 신고할 때마다 추가
        this.sql.insert("Report.insertReportDetail", reportRequest);
    }

    public List<Report> getAllReports() {
        return this.sql.selectList("Report.selectAllReports");
    }

    public List<Report> getReportsByCategory(int category) {
        return this.sql.selectList("Report.selectReportsByCategory", category);
    }

}
