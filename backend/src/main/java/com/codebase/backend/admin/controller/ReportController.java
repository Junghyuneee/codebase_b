package com.codebase.backend.admin.controller;

import com.codebase.backend.admin.dto.Report;
import com.codebase.backend.admin.dto.ReportDTO;
import com.codebase.backend.admin.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @CrossOrigin(origins = "http://localhost:5713")
    @PostMapping("/create")
    public void createReport(@RequestBody Report report) {
        // ResponseEntity<?> 리턴 타입 => 성공 여부를 프런트로 전달해서 성공/실패를 판단할 수 있나?
        System.out.println("신고 넘어옴 : "+report.getContent());

        try {
            // 신고 데이터 처리
            reportService.save(report);

//            // 성공 응답 (JSON 형식)
//            return ResponseEntity.ok(Map.of("message", "신고가 성공적으로 접수되었습니다."));
        } catch (Exception e) {
            e.printStackTrace();

//            // 실패 응답 (JSON 형식)
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(Map.of("message", "신고 처리 중 오류가 발생했습니다."));
        }
    }

    @CrossOrigin(origins = "http://localhost:5713")
    @GetMapping("/readAll")
    public List<ReportDTO> getAllReport() {
        System.out.println(reportService.getAllReport().get(0).getMemberName());
        return reportService.getAllReport();
    }

}
