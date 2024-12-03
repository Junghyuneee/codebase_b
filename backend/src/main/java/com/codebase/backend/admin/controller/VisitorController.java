package com.codebase.backend.admin.controller;

import com.codebase.backend.admin.dto.Visitor;
import com.codebase.backend.admin.service.VisitorService;
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
@RequestMapping("/dashboard/visitor")
public class VisitorController {

    @Autowired
    private VisitorService visitorService;

    @CrossOrigin(origins = "http://localhost:5713")
    @GetMapping("/get-ip")
    public String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-for");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        Visitor visitor = new Visitor(ip, LocalDateTime.now());
        visitorService.save(visitor);
        return "클라이언트 IP 주소" + ip;
    }

    @CrossOrigin(origins = "http://localhost:5713")
    @GetMapping("/weekly")
    public List<Map<String, Object>> getWeeklyVisitorCount() {
        return visitorService.getWeeklyVisitorCount();
    }
}
