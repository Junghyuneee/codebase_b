/*
김은지
2024 11 25
*/
package com.codebase.backend.admin.repository;

import com.codebase.backend.admin.dto.Report;
import com.codebase.backend.admin.dto.ReportDetail;
import com.codebase.backend.admin.dto.ReportRequest;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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

    public List<Report> getAllReports(Map<String, Object> params) {
        return this.sql.selectList("Report.selectAllReports", params);
    }

    public int countAllReports() {
        return this.sql.selectOne("Report.countAllReports");
    }

    public List<Report> getReportsByCategory(Map<String, Object> params) {
        return this.sql.selectList("Report.selectReportsByCategory", params);
    }

    public int countReportsByCategory(int category) {
        return this.sql.selectOne("Report.countReportsByCategory", category);
    }

    public List<ReportDetail> getReportDetails(int reportId) {
        return this.sql.selectList("Report.selectReportDetails", reportId);
    }

    public void processReport(int reportId) {
        this.sql.selectList("Report.processReport", reportId);
    }

    public void deleteMemberId(int memberId) {
        this.sql.selectList("Report.deleteMemberId", memberId);
    }

}
