package com.codebase.backend.projectteam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codebase.backend.projectteam.dto.Status;
import com.codebase.backend.projectteam.dto.TeamApplicationDTO;
import com.codebase.backend.projectteam.mapper.TeamApplicationMapper;

@Service
@Transactional
public class TeamApplicationService {
    
    @Autowired
    private TeamApplicationMapper teamApplicationMapper;
    
    public List<TeamApplicationDTO> getTeamApplications() {
        return teamApplicationMapper.getTeamApplications();
    }

    public TeamApplicationDTO getTeamApplicationById(Integer application_id) {
        return teamApplicationMapper.getTeamApplicationById(application_id);
    }

    public void insertTeamApplication(TeamApplicationDTO teamApplication) {
        if (teamApplication.getPjt_id() == null || teamApplication.getPjt_id() == 0) {  
            teamApplication.setPjt_id(0);
        }
        teamApplicationMapper.insertTeamApplication(teamApplication);
    }

    public void updateTeamApplication(TeamApplicationDTO teamApplication) {
        teamApplicationMapper.updateTeamApplication(teamApplication);
    }

    public void deleteTeamApplication(Integer application_id) {
        teamApplicationMapper.deleteTeamApplication(application_id);
    }
    
    public List<TeamApplicationDTO> getTeamMembers(Integer pjt_Id) {
        List<TeamApplicationDTO> members = teamApplicationMapper.findTeamMembersByProjectId(pjt_Id);

        members.forEach(member -> {
            if (member.getStatus() != null) {  // null 체크 추가
                try {
                    member.setStatus(Status.valueOf(member.getStatus().name()));
                } catch (IllegalArgumentException e) {
                    member.setStatus(Status.UNKNOWN);
                }
            } else {
                member.setStatus(Status.PENDING);  // 기본값 설정
            }
        });

        return members;
    }
}
