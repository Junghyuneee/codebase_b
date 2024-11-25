package com.codebase.backend.project.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.codebase.backend.project.dto.Project;
import com.codebase.backend.project.service.ProjectService;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:5173" })
public class ProjectController {

	private final ProjectService projectService;

	@GetMapping("/api/store")
	@ResponseBody
	public List<Project> getProjects() {
		System.out.println(projectService.readlist());

		return projectService.readlist();

	}
	
	@GetMapping("/api/store/{id}")
	@ResponseBody
	public Project projectFindById(@PathVariable("id") Integer id) {
		//System.out.println(id);
		
		return projectService.findById(id);
	}
	

	@PostMapping("/api/store")
	public ResponseEntity<String> postTest(@RequestBody Map<String, Object> data) {
		System.out.println(data.toString());

		return ResponseEntity.ok("string 맞추기");
	}
}
