package com.codebase.backend.projectteam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codebase.backend.projectteam.dto.TeamApplicationDTO;
import com.codebase.backend.projectteam.service.TeamApplicationService;

@RestController
@RequestMapping("/api/team-applications")
public class TeamApplicationController {

    @Autowired
    private TeamApplicationService teamApplicationService;

    @GetMapping
    public List<TeamApplicationDTO> getTeamApplications() {
        return teamApplicationService.getTeamApplications();
    }

    @GetMapping("/{application_id}")
    public TeamApplicationDTO getTeamApplicationById(@PathVariable Integer application_id) {
        return teamApplicationService.getTeamApplicationById(application_id);
    }

    @PostMapping
    public ResponseEntity<Void> insertTeamApplication(@RequestBody TeamApplicationDTO teamApplication) {
        teamApplicationService.insertTeamApplication(teamApplication);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }  

    @PutMapping("/{application_id}")
    public void updateTeamApplication(@PathVariable Integer application_id, @RequestBody TeamApplicationDTO teamApplication) {
        teamApplicationService.updateTeamApplication(teamApplication);
    }  

    @DeleteMapping("/{application_id}")
    public void deleteTeamApplication(@PathVariable Integer application_id) {
        teamApplicationService.deleteTeamApplication(application_id);
    }   

}
