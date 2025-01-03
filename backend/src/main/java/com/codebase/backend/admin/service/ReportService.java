/*
김은지
2024 11 25
*/
package com.codebase.backend.admin.service;

import com.codebase.backend.admin.dto.Report;
import com.codebase.backend.admin.dto.ReportDetail;
import com.codebase.backend.admin.dto.ReportDetailResponse;
import com.codebase.backend.admin.dto.ReportRequest;
import com.codebase.backend.admin.repository.ReportRepository;
import com.codebase.backend.member.dto.Member;
import com.codebase.backend.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ReportRepository reportRepository;
    private final MemberService memberService;

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

    public List<ReportDetailResponse> getReportDetails(int reportId) {
        List<ReportDetail> reportDetails = this.reportRepository.getReportDetails(reportId);
        List<ReportDetailResponse> reportDetailResponses = new ArrayList<>();
        for(ReportDetail reportDetail : reportDetails) {
            ReportDetailResponse reportDetailResponse = new ReportDetailResponse();

            reportDetailResponse.setDetailId(reportDetail.getDetailId());
            reportDetailResponse.setReportId(reportDetail.getReportId());

            Member member = memberService.getMemberById(reportDetail.getMemberId());
            reportDetailResponse.setMemberEmail(member.getEmail());

            reportDetailResponse.setContent(reportDetail.getContent());

            LocalDateTime reportDate = reportDetail.getReportDate();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH:mm:ss");
            reportDetailResponse.setReportDate(reportDate.format(formatter));

            reportDetailResponses.add(reportDetailResponse);
        }
        return reportDetailResponses;
    }

    public void processReport(int reportId) {
        this.reportRepository.processReport(reportId);
    }

}
