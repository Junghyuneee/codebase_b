package com.codebase.backend.projectteam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codebase.backend.projectteam.DTO.ProjectteamDTO;
import com.codebase.backend.projectteam.mapper.ProjectteamMapper;

import java.util.List;

@Service
public class ProjectteamService {

    @Autowired
    private ProjectteamMapper projectteamMapper;

    // 특정 프로젝트 조회
    public ProjectteamDTO getProjectTeamById(Integer id) {
        return projectteamMapper.getProjectTeamById(id);
    }

    // 모든 프로젝트 조회
    public List<ProjectteamDTO> getAllProjectTeams() {
        return projectteamMapper.getAllProjectTeams();
    }

    // 새 프로젝트 생성
    public void createProjectTeam(ProjectteamDTO projectTeam) {
        projectteamMapper.insertProjectTeam(projectTeam);
    }

    // 프로젝트 수정
    public void updateProjectTeam(ProjectteamDTO projectTeam) {
        projectteamMapper.updateProjectTeam(projectTeam);
    }

    // 프로젝트 삭제
    public void deleteProjectTeam(Integer id) {
        projectteamMapper.deleteProjectTeam(id);
    }
}
