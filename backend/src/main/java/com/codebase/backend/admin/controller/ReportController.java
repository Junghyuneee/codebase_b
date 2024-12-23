/*
김은지
2024 11 25
*/
package com.codebase.backend.admin.controller;

import com.codebase.backend.admin.dto.ReportDetail;
import com.codebase.backend.admin.dto.ReportRequest;
import com.codebase.backend.admin.service.ReportService;
import com.codebase.backend.member.dto.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> createReport(@RequestBody ReportRequest reportRequest, @AuthenticationPrincipal Member member) {
        // RequestBody = category, categoryId, categoryTitle, content

        Map<String, Object> response = new HashMap<>();
        try {
            // 신고 데이터 처리
            reportService.saveReport(reportRequest); // 신고 테이블
            reportService.saveReportDetail(reportRequest, member); // 신고 상세 테이블
            // 성공 응답 (JSON 형식)
            response.put("title", reportRequest.getCategoryTitle());
            response.put("status", "success"); // 성공/실패 상태는 프런트에서 사용 안하는데..
            response.put("message", "게시글의 신고가 성공적으로 접수되었습니다.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            // 실패 응답 (JSON 형식)
            response.put("title", reportRequest.getCategoryTitle());
            response.put("status", "fail");
            response.put("message", "게시글의 신고 접수에 실패했습니다. 다시 시도해주세요.");
            return ResponseEntity.ok(response);
        }
    }

    @CrossOrigin(origins = "http://localhost:5713")
    @GetMapping("/read/{category}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, Object>> getAllReport(@PathVariable int category,
                                                            @RequestParam(defaultValue = "1") int page,
                                                            @RequestParam(defaultValue = "10") int size) {
        Map<String, Object> response = reportService.getReports(category, page, size);
        return ResponseEntity.ok(response);
    }

    @CrossOrigin(origins = "http://localhost:5713")
    @GetMapping("/details/{reportId}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<ReportDetail> getReportDetails(@PathVariable int reportId) {
        return reportService.getReportDetails(reportId);
    }

    @CrossOrigin(origins = "http://localhost:5713")
    @PostMapping("/process/{reportId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> processReport(@PathVariable int reportId) {
        Map<String, Object> response = new HashMap<>();
        try {
            // 신고 처리
            reportService.processReport(reportId);

            // 성공 응답 (JSON 형식)
            response.put("title", "타이틀");
            response.put("status", "success");
            response.put("message", "게시글의 신고가 성공적으로 처리되었습니다.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            // 실패 응답 (JSON 형식)
            response.put("title", "타이틀");
            response.put("status", "fail");
            response.put("message", "게시글의 신고 처리에 실패했습니다.");
            return ResponseEntity.ok(response);
        }
    }
}
