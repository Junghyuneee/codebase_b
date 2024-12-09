/*
김은지
2024 11 25
*/
package com.codebase.backend.admin.service;

import com.codebase.backend.admin.dto.Report;
import com.codebase.backend.admin.dto.ReportRequest;
import com.codebase.backend.admin.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    public void saveReport(ReportRequest reportRequest) {
        this.reportRepository.saveReport(reportRequest);
    }

    public void saveReportDetail(ReportRequest reportRequest) {
        this.reportRepository.saveReportDetail(reportRequest);
    }

    public List<Report> getReports(int category) {

        if(category == 4) {

            return this.reportRepository.getAllReports()
                    .stream()
                    .map(report -> new Report(
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
                .map(report -> new Report(
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
