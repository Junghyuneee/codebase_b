/*
김은지
2024 11 25
*/
package com.codebase.backend.admin.service;

import com.codebase.backend.admin.dto.Report;
import com.codebase.backend.admin.dto.ReportDetails;
import com.codebase.backend.admin.dto.ReportDTO;
import com.codebase.backend.admin.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    public void saveReport(ReportDetails reportDetail) {
        Report report = new Report(reportDetail.getCategory(),
                reportDetail.getCategoryId(), reportDetail.getCategoryTitle());
        this.reportRepository.saveReport(report);
    }

    public void saveReportDetail(ReportDetails report) {
        this.reportRepository.saveReportDetail(report);
    }

    public List<ReportDTO> getReports(int category) {

        if(category == 4) {

            return this.reportRepository.getAllReports()
                    .stream()
                    .map(report -> new ReportDTO(
                            report.getReportId(),
                            report.getCategory(),
                            report.getCategoryId(),
                            report.getCategoryTitle(),
                            report.getCount(),
                            report.isCompleted()
                    ))
                    .toList();
        }

        return this.reportRepository.getReportsByCategory(category)
                .stream()
                .map(report -> new ReportDTO(
                        report.getReportId(),
                        report.getCategory(),
                        report.getCategoryId(),
                        report.getCategoryTitle(),
                        report.getCount(),
                        report.isCompleted()
                ))
                .toList();
    }

}
