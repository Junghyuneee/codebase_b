package com.codebase.backend.projectteam.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.codebase.backend.projectteam.dto.ProjectteamDTO;

@Mapper
public interface ProjectteamMapper {

    ProjectteamDTO getProjectTeamById(Integer id);
    
    List<ProjectteamDTO> getAllProjectTeams();
    
    void insertProjectTeam(ProjectteamDTO projectTeam);
    
    void updateProjectTeam(ProjectteamDTO projectTeam);
    
    void deleteProjectTeam(Integer id);
    
    List<ProjectteamDTO> getProjectTeamsByMemberId(Integer member_id);
    
    List<ProjectteamDTO> getProjectTeamsByCategory(String pjcategory);
    
    Integer countProjectTeamsByMemberId(Integer member_id);
    
    boolean existsById(Integer pjt_id);
}
