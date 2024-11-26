package com.codebase.backend.projectteam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.codebase.backend.projectteam.DTO.ProjectteamDTO;
import com.codebase.backend.projectteam.service.ProjectteamService;

import java.util.List;

@RestController
@RequestMapping("/api/projectteams")
public class ProjectteamController {

    @Autowired
    private ProjectteamService projectTeamService;

    // 특정 프로젝트 조회
    @GetMapping("/{pjt_id}")
    public ResponseEntity<ProjectteamDTO> getProjectTeamById(@PathVariable("pjt_id") Integer id) {
        ProjectteamDTO projectTeam = projectTeamService.getProjectTeamById(id);
        if (projectTeam != null) {
            return ResponseEntity.ok(projectTeam);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 모든 프로젝트 조회
    @GetMapping
    public ResponseEntity<List<ProjectteamDTO>> getAllProjectTeams() {
        List<ProjectteamDTO> projectTeams = projectTeamService.getAllProjectTeams();
        return ResponseEntity.ok(projectTeams);
    }

    // 새 프로젝트 생성
    @PostMapping
    public ResponseEntity<Void> createProjectTeam(@RequestBody ProjectteamDTO projectTeam) {
        projectTeamService.createProjectTeam(projectTeam);
        return ResponseEntity.ok().build();
    }

    // 프로젝트 수정
    @PutMapping("/{pjt_id}")
    public ResponseEntity<Void> updateProjectTeam(@PathVariable("pjt_id") Integer id, @RequestBody ProjectteamDTO projectTeam) {
        projectTeam.setPjtId(id);
        projectTeamService.updateProjectTeam(projectTeam);
        return ResponseEntity.ok().build();
    }

    // 프로젝트 삭제
    @DeleteMapping("/{pjt_id}")
    public ResponseEntity<Void> deleteProjectTeam(@PathVariable("pjt_id") Integer id) {
        projectTeamService.deleteProjectTeam(id);
        return ResponseEntity.ok().build();
    }
}
