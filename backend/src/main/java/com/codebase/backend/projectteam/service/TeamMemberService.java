package com.codebase.backend.projectteam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codebase.backend.projectteam.dto.TeamMemberDTO;
import com.codebase.backend.projectteam.mapper.TeamMemberMapper;

@Service
public class TeamMemberService {
    
    @Autowired
    private TeamMemberMapper teamMemberMapper;
    
    public TeamMemberDTO createTeamMember(TeamMemberDTO teamMember) {
        teamMemberMapper.insertTeamMember(teamMember);
        return teamMember;
    }
    
    public TeamMemberDTO getTeamMember(Integer tmId) {
        return teamMemberMapper.selectTeamMember(tmId);
    }
    
    public List<TeamMemberDTO> getTeamMembersByProject(Integer projectId) {
        return teamMemberMapper.selectTeamMembersByProject(projectId);
    }
    
    public TeamMemberDTO updateTeamMember(TeamMemberDTO teamMember) {
        teamMemberMapper.updateTeamMember(teamMember);
        return teamMember;
    }
    
    public boolean deleteTeamMember(Integer tmId) {
        return teamMemberMapper.deleteTeamMember(tmId) > 0;
    }
    

} 