package com.codebase.backend.projectteam.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.codebase.backend.projectteam.dto.TeamApplicationDTO;

@Mapper
public interface TeamApplicationMapper {

    List<TeamApplicationDTO> getTeamApplications();

    TeamApplicationDTO getTeamApplicationById(Integer application_id);

    void insertTeamApplication(TeamApplicationDTO teamApplication);

    void updateTeamApplication(TeamApplicationDTO teamApplication);

    void deleteTeamApplication(Integer application_id);

    List<TeamApplicationDTO> getTeamApplicationsByMemberId(Integer member_id);
    
    List<TeamApplicationDTO> findTeamMembersByProjectId(@Param("pjtId") Integer pjtId);

}
