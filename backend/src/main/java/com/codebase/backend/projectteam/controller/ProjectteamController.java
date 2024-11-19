package com.codebase.backend.projectteam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codebase.backend.projectteam.DTO.ProjectteamDTO;
import com.codebase.backend.projectteam.service.ProjectteamService;

@RestController
@RequestMapping("/api/projectteams")
public class ProjectteamController {

    @Autowired
    private ProjectteamService projectTeamService;

    @GetMapping("/{id}")
    public ResponseEntity<ProjectteamDTO> getProjectTeamById(@PathVariable Integer id) {
        ProjectteamDTO projectTeam = projectTeamService.getProjectTeamById(id);
        if (projectTeam != null) {
            return ResponseEntity.ok(projectTeam);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}