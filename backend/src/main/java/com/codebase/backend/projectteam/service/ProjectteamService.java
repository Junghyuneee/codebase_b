package com.codebase.backend.projectteam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codebase.backend.projectteam.DTO.ProjectteamDTO;
import com.codebase.backend.projectteam.mapper.ProjectteamMapper;

@Service
public class ProjectteamService {

    @Autowired
    private ProjectteamMapper projectteamMapper;

    public ProjectteamDTO getProjectTeamById(Integer id) {
        return projectteamMapper.getProjectTeamById(id);
    }
}