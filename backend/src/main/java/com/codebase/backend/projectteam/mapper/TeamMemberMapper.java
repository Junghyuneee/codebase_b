package com.codebase.backend.projectteam.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.codebase.backend.projectteam.dto.TeamMemberDTO;

@Mapper
public interface TeamMemberMapper {
    
    int insertTeamMember(TeamMemberDTO teamMember);
    TeamMemberDTO selectTeamMember(Integer tm_id);
    List<TeamMemberDTO> selectTeamMembersByProject(Integer pjt_id);
    int updateTeamMember(TeamMemberDTO teamMember);
    int deleteTeamMember(Integer tm_id);
}
