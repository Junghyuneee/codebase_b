package com.codebase.backend.admin.service;

import com.codebase.backend.admin.dto.Report;
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

    public void  save(Report report) {
        report.setDate(LocalDateTime.now());
        report.setCompleted(false);

        this.reportRepository.saveReport(report);
    }

    public List<ReportDTO> getAllReport() {
        return this.reportRepository.getAllReport()
                .stream()
                .map(report -> new ReportDTO(
                        report.getReportId(),
                        report.getContent(),
                        report.getCategory(),
                        report.getCategoryId(),
                        report.getCategoryTitle(),
                        report.getMemberName(),
                        report.isCompleted()
                ))
                .toList();
    }

}
