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
import com.codebase.backend.member.dto.Member;
import com.codebase.backend.member.service.MemberService;
import com.codebase.backend.projectteam.dto.ProjectteamDTO;
import com.codebase.backend.projectteam.service.ProjectteamService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@RestController
@RequestMapping("/api/projectteams")
public class ProjectteamController {

    @Autowired
    private ProjectteamService projectTeamService;
    
    @Autowired
    private S3Service s3Service;

    @Autowired
    private MemberService memberService;
    
    private final ObjectMapper objectMapper;

    public ProjectteamController() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
        this.objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

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
            ProjectteamDTO projectTeam = objectMapper.readValue(projectTeamJson, ProjectteamDTO.class);
            
            // member_id로 회원 정보 조회하여 이름을 pjtowner에 설정
            Member member = memberService.getMemberById(projectTeam.getMemberId());
            if (member != null) {
                projectTeam.setPjtowner(member.getName());
            }

            if (file != null && !file.isEmpty()) {
                String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
                s3Service.uploadFile(file, fileName);
                projectTeam.setPjtimg(fileName);
            }

            projectTeamService.createProjectTeam(projectTeam, file);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


    // 프로젝트 수정
    @PutMapping("/{pjt_id}")
    public ResponseEntity<Void> updateProjectTeam(@PathVariable("pjt_id") Integer id, @RequestBody ProjectteamDTO projectTeam) {
        projectTeam.setPjt_id(id);
        projectTeamService.updateProjectTeam(projectTeam);
        return ResponseEntity.ok().build();
    }

    // 프로젝트 삭제
    @DeleteMapping("/{pjt_id}")
    public ResponseEntity<Void> deleteProjectTeam(@PathVariable("pjt_id") Integer id) {
        projectTeamService.deleteProjectTeam(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/member/{member_id}")
    public ResponseEntity<List<ProjectteamDTO>> getProjectTeamsByMemberId(@PathVariable("member_id") Integer memberId) {
        List<ProjectteamDTO> projectTeams = projectTeamService.getProjectTeamsByMemberId(memberId);
        return ResponseEntity.ok(projectTeams);
    }

    @GetMapping("/category/{pjcategory}")
    public ResponseEntity<List<ProjectteamDTO>> getProjectTeamsByCategory(@PathVariable("pjcategory") String category) {
        List<ProjectteamDTO> projectTeams = projectTeamService.getProjectTeamsByCategory(category);
        return ResponseEntity.ok(projectTeams);
    }

    @GetMapping("/count/member/{member_id}")
    public ResponseEntity<Integer> countProjectTeamsByMemberId(@PathVariable("member_id") Integer memberId) {
        Integer count = projectTeamService.countProjectTeamsByMemberId(memberId);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/exists/{pjt_id}")
    public ResponseEntity<Boolean> checkProjectTeamExists(@PathVariable("pjt_id") Integer projectId) {
        boolean exists = projectTeamService.existsProjectTeam(projectId);
        return ResponseEntity.ok(exists);
    }
}
