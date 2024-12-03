package com.codebase.backend.admin.service;

import com.codebase.backend.admin.dto.NewMemberResponse;
import com.codebase.backend.admin.dto.PopularResponse;
import com.codebase.backend.admin.dto.Visitor;
import com.codebase.backend.admin.repository.DashboardRepository;

import com.codebase.backend.project.mapper.ProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DashboardService {

    @Autowired
    private DashboardRepository dashboardRepository;

    @Autowired
    private ProjectMapper projectMapper;

    // 방문자 수
    public void  save(Visitor visitor) {
        if(!this.dashboardRepository.isIpRecordedRecently(visitor)) {
            this.dashboardRepository.saveVisitor(visitor);
            System.out.println("저장된 IP 주소 : " + visitor.getVisitIp());
        } else {
            System.out.println("중복된 IP 주소 : " + visitor.getVisitIp());
        }
    }

    public List<Map<String, Object>> getWeeklyVisitorCount() {
        return this.dashboardRepository.getWeeklyVisitorCount();
    }

    // 인기 프로젝트
    public List<PopularResponse> getPopularProjects() {
        return projectMapper.findPopularProjects();
    }
    
    // 신규 회원 수
    public List<NewMemberResponse> getWeeklyNewMemberCount() {
        List<Map<String, Object>> resultNewMemberCount = this.dashboardRepository.getWeeklyNewMemberCount();
        return resultNewMemberCount.stream()
                .map(row -> new NewMemberResponse(
                        ((Number) row.get("memberCount")).intValue(),
                        ((Date) row.get("joinDate")).toLocalDate()
                ))
                .collect(Collectors.toList());
    }

}

