package com.codebase.backend.projectteam.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.codebase.backend.configs.S3Service;
import com.codebase.backend.projectteam.DTO.ProjectteamDTO;
import com.codebase.backend.projectteam.service.ProjectteamService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/projectteams")
public class ProjectteamController {

    @Autowired
    private ProjectteamService projectTeamService;
    
    @Autowired
    private S3Service s3Service;

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
    public ResponseEntity<Void> createProjectTeam(
            @RequestPart("projectTeam") String projectTeamJson,
            @RequestPart("file") MultipartFile file) {
        try {
            System.out.println("Received projectTeam: " + projectTeamJson);
            System.out.println("Received file: " + (file != null ? file.getOriginalFilename() : "No file"));

            ObjectMapper objectMapper = new ObjectMapper();
            ProjectteamDTO projectTeam = objectMapper.readValue(projectTeamJson, ProjectteamDTO.class);

            if (file != null && !file.isEmpty()) {
                String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
                s3Service.uploadFile(file, fileName);
                projectTeam.setPjtimg(fileName);
            }

            projectTeamService.createProjectTeam(projectTeam,file);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
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
