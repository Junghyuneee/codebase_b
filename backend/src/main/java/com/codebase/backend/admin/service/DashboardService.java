package com.codebase.backend.admin.service;

import com.codebase.backend.admin.dto.NewMemberResponse;
import com.codebase.backend.admin.dto.PopularResponse;
import com.codebase.backend.admin.dto.Visitor;
import com.codebase.backend.admin.repository.DashboardRepository;

import com.codebase.backend.post.mapper.PostMapper;
import com.codebase.backend.project.mapper.ProjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final DashboardRepository dashboardRepository;
    private final ProjectMapper projectMapper;
    private final PostMapper postMapper;

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
        LocalDate startDate = LocalDate.now().minusDays(6);
        LocalDate endDate = LocalDate.now();

        List<Map<String, Object>> weeklyVisitorCount= this.dashboardRepository.getWeeklyVisitorCount();

        // DB 결과를 Map으로 변환 (날짜를 기준으로 쉽게 조회 가능하게 만듦)
        Map<LocalDate, Integer> visitorCountMap = weeklyVisitorCount.stream()
                .collect(Collectors.toMap(
                        item -> LocalDate.parse(item.get("visit_date").toString()), // 날짜 키
                        item -> ((Number) item.get("visitor_count")).intValue()    // 방문자 수 값
                ));

        // 모든 날짜를 순회하며 없는 날짜는 방문자 수 0으로 추가
        List<Map<String, Object>> completeVisitorCount = new ArrayList<>();
        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            Map<String, Object> dailyVisitor = new HashMap<>();
            dailyVisitor.put("visit_date", date.toString()); // 날짜를 String으로 추가
            dailyVisitor.put("visitor_count", visitorCountMap.getOrDefault(date, 0)); // 방문자 수 추가
            completeVisitorCount.add(dailyVisitor);
        }

        return completeVisitorCount;
    }
    
    // 신규 회원 수
    public List<NewMemberResponse> getWeeklyNewMemberCount() {
        LocalDate startDate = LocalDate.now().minusDays(6);
        LocalDate endDate = LocalDate.now();

        List<Map<String, Object>> newMemberCount = this.dashboardRepository.getWeeklyNewMemberCount();

        // DB 결과를 Map으로 변환 (날짜를 기준으로 조회할 수 있도록 만듦)
        Map<LocalDate, Integer> memberCountMap = newMemberCount.stream()
                .collect(Collectors.toMap(
                        row -> ((Date) row.get("joinDate")).toLocalDate(), // 날짜 키
                        row -> ((Number) row.get("memberCount")).intValue() // 회원 수 값
                ));

        // 모든 날짜에 대해 신규 회원 데이터를 생성
        List<NewMemberResponse> completeNewMemberCount = new ArrayList<>();
        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            int count = memberCountMap.getOrDefault(date, 0); // 해당 날짜의 회원 수, 없으면 0
            completeNewMemberCount.add(new NewMemberResponse(count, date));
        }

        return completeNewMemberCount;
    }

    // 인기 프로젝트
    public List<PopularResponse> getPopularProjects() {
        return projectMapper.findPopularProjects();
    }

    // 인기 자유게시글
    public List<PopularResponse> getPopularPosts() {
        return postMapper.findPopularPosts();
    }

}

