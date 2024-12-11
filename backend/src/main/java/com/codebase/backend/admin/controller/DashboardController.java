package com.codebase.backend.admin.controller;

import com.codebase.backend.admin.dto.NewMemberResponse;
import com.codebase.backend.admin.dto.PopularResponse;
import com.codebase.backend.admin.dto.Visitor;
import com.codebase.backend.admin.service.DashboardService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:5713")
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    // 방문자 수
    @GetMapping("/visitor/get-ip")
    public String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-for");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        Visitor visitor = new Visitor(ip, LocalDateTime.now());
        dashboardService.save(visitor);
        return "클라이언트 IP 주소" + ip;
    }

    @GetMapping("/visitor/weekly")
    public List<Map<String, Object>> getWeeklyVisitorCount() {
        return dashboardService.getWeeklyVisitorCount();
    }

    // 신규 회원 수
    @GetMapping("/newMember/weekly")
    public List<NewMemberResponse> newMemberWeekly() {
        return dashboardService.getWeeklyNewMemberCount();
    }

    // 인기 프로젝트
    @GetMapping("/popular/project")
    public List<PopularResponse> popularProjects() {
        return dashboardService.getPopularProjects();
    }


    // 인기 자유게시글
    @GetMapping("/popular/post")
    public List<PopularResponse> popularPosts() {
        return dashboardService.getPopularPosts();
    }


}
