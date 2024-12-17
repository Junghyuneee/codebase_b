package com.codebase.backend.projectteam.controller;

import com.codebase.backend.projectteam.dto.TeamMemberDTO;
import com.codebase.backend.projectteam.service.TeamMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teammembers")
public class TeamMemberController {

    @Autowired
    private TeamMemberService teamMemberService;

    @PostMapping
    public ResponseEntity<TeamMemberDTO> createTeamMember(@RequestBody TeamMemberDTO teamMember) {
        TeamMemberDTO createdMember = teamMemberService.createTeamMember(teamMember);
        return ResponseEntity.ok(createdMember);
    }

    @GetMapping("/{tmId}")
    public ResponseEntity<TeamMemberDTO> getTeamMember(@PathVariable Integer tmId) {
        TeamMemberDTO teamMember = teamMemberService.getTeamMember(tmId);
        if (teamMember != null) {
            return ResponseEntity.ok(teamMember);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<TeamMemberDTO>> getTeamMembersByProject(@PathVariable Integer projectId) {
        List<TeamMemberDTO> members = teamMemberService.getTeamMembersByProject(projectId);
        return ResponseEntity.ok(members);
    }

    @PutMapping("/{tmId}")
    public ResponseEntity<TeamMemberDTO> updateTeamMember(
            @PathVariable Integer tmId,
            @RequestBody TeamMemberDTO teamMember) {
        teamMember.setTm_id(tmId);
        TeamMemberDTO updatedMember = teamMemberService.updateTeamMember(teamMember);
        return ResponseEntity.ok(updatedMember);
    }

    @DeleteMapping("/{tmId}")
    public ResponseEntity<Void> deleteTeamMember(@PathVariable Integer tmId) {
        if (teamMemberService.deleteTeamMember(tmId)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
} 