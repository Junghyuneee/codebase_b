package com.codebase.backend.projectteam.service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.codebase.backend.configs.S3Service;
import com.codebase.backend.projectteam.DTO.ProjectteamDTO;
import com.codebase.backend.projectteam.mapper.ProjectteamMapper;

@Service
public class ProjectteamService {

    @Autowired
    private ProjectteamMapper projectteamMapper;
    
    @Autowired
    private S3Service s3Service;

    // 특정 프로젝트 조회
    public ProjectteamDTO getProjectTeamById(Integer id) {
        return projectteamMapper.getProjectTeamById(id);
    }

    // 모든 프로젝트 조회
    public List<ProjectteamDTO> getAllProjectTeams() {
        return projectteamMapper.getAllProjectTeams();
    }

    // 새 프로젝트 생성
    public void createProjectTeam(ProjectteamDTO projectTeam, MultipartFile file) throws IOException {
    	
    	if ( file != null && !file.isEmpty()) {
    		UUID uuid = UUID.randomUUID();
    		String fileName = uuid + "_" + file.getOriginalFilename();
    		
    		s3Service.uploadFile(file, fileName);
    		
    		projectTeam.setPjtimg(fileName);
    	}   		
    	
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

    public List<ProjectteamDTO> getProjectTeamsByMemberId(Integer memberId) {
        return projectteamMapper.getProjectTeamsByMemberId(memberId);
    }

    public List<ProjectteamDTO> getProjectTeamsByCategory(String category) {
        return projectteamMapper.getProjectTeamsByCategory(category);
    }

    public Integer countProjectTeamsByMemberId(Integer memberId) {
        return projectteamMapper.countProjectTeamsByMemberId(memberId);
    }

    public boolean existsProjectTeam(Integer projectId) {
        return projectteamMapper.existsById(projectId);
    }
}
