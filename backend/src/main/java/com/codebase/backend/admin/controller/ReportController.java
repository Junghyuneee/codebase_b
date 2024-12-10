/*
김은지
2024 11 25
*/
package com.codebase.backend.admin.controller;

import com.codebase.backend.admin.dto.Report;
import com.codebase.backend.admin.dto.ReportDetail;
import com.codebase.backend.admin.dto.ReportRequest;
import com.codebase.backend.admin.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @CrossOrigin(origins = "http://localhost:5713")
    @PostMapping("/create")
    public ResponseEntity<?> createReport(@RequestBody ReportRequest reportRequest) {
        // RequestBody = category, categoryId, categoryTitle memberId, content

        // ResponseEntity<?> 리턴 타입 => 성공 여부를 프런트로 전달해서 성공/실패를 판단할 수 있나?
        Map<String, Object> response = new HashMap<>();
        try {
            // 신고 데이터 처리
            reportService.saveReport(reportRequest); // 신고 테이블
            reportService.saveReportDetail(reportRequest); // 신고 상세 테이블
            // 성공 응답 (JSON 형식)
            response.put("title", reportRequest.getCategoryTitle());
            response.put("status", "success");
            response.put("message", "게시글의 신고가 성공적으로 접수되었습니다.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            // 실패 응답 (JSON 형식)
            response.put("title", reportRequest.getCategoryTitle());
            response.put("status", "fail");
            response.put("message", "게시글의 신고 접수에 실패했습니다.");
            return ResponseEntity.ok(response);
        }
    }

    @CrossOrigin(origins = "http://localhost:5713")
    @GetMapping("/read/{category}")
    public ResponseEntity<Map<String, Object>> getAllReport(@PathVariable int category,
                                     @RequestParam(defaultValue = "1") int page,
                                     @RequestParam(defaultValue = "10") int size) {
        Map<String, Object> response = reportService.getReports(category, page, size);
        return ResponseEntity.ok(response);
    }

    @CrossOrigin(origins = "http://localhost:5713")
    @GetMapping("/details/{reportId}")
    public List<ReportDetail> getReportDetails(@PathVariable int reportId) {
        return reportService.getReportDetails(reportId);
    }

}
