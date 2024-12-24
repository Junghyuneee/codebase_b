package com.codebase.backend.projectteam.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.codebase.backend.projectteam.dto.TeamApplicationDTO;

import java.util.List;

@Mapper
public interface TeamApplicationMapper {

    List<TeamApplicationDTO> getTeamApplications();

    TeamApplicationDTO getTeamApplicationById(Integer application_id);

    void insertTeamApplication(TeamApplicationDTO teamApplication);

    void updateTeamApplication(TeamApplicationDTO teamApplication);

    void deleteTeamApplication(Integer application_id);

    List<TeamApplicationDTO> getTeamApplicationsByMemberId(Integer member_id);

}
