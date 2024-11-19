package com.codebase.backend.projectteam.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.codebase.backend.projectteam.DTO.ProjectteamDTO;

@Mapper
public interface ProjectteamMapper {

    ProjectteamDTO getProjectTeamById(Integer id);
    
    List<ProjectteamDTO> getAllProjectTeams();
    
    void insertProjectTeam(ProjectteamDTO projectTeam);
    
    void updateProjectTeam(ProjectteamDTO projectTeam);
    
    void deleteProjectTeam(Integer id);
}
