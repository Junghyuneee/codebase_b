/*
김은지
2024 11 25
*/
package com.codebase.backend.admin.service;

import com.codebase.backend.admin.dto.Report;
import com.codebase.backend.admin.dto.ReportDetail;
import com.codebase.backend.admin.dto.ReportRequest;
import com.codebase.backend.admin.repository.ReportRepository;
import com.codebase.backend.member.dto.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    public void saveReport(ReportRequest reportRequest) {
        this.reportRepository.saveReport(reportRequest);
    }

    public void saveReportDetail(ReportRequest reportRequest, @AuthenticationPrincipal Member member) {
        reportRequest.setMemberId(member.getId());
        this.reportRepository.saveReportDetail(reportRequest);
    }

    public Map<String, Object> getReports(int category, int page, int size) {
        int offset = (page - 1) * size;
        Map<String, Object> params = new HashMap<>();
        Map<String, Object> response = new HashMap<>();
        if(category == 4) {
            params.put("offset", offset);
            params.put("limit", size);
            int totalData = reportRepository.countAllReports();

            List<Report> reports = reportRepository.getAllReports(params);

            response.put("data", reports);
            response.put("totalPages", (int) Math.ceil((double) totalData / size));
            response.put("currentPage", page);
            return response;
        }

        params.put("category", category);
        params.put("offset", offset);
        params.put("limit", size);
        int totalData = reportRepository.countReportsByCategory(category);

        List<Report> reportsByCategory = reportRepository.getReportsByCategory(params);

        response.put("data", reportsByCategory);
        response.put("totalPages", (int) Math.ceil((double) totalData / size));
        response.put("currentPage", page);
        return response;
    }

    public List<ReportDetail> getReportDetails(int reportId) {
        return this.reportRepository.getReportDetails(reportId);
    }

    public void processReport(int reportId) {
        this.reportRepository.processReport(reportId);
    }

}
