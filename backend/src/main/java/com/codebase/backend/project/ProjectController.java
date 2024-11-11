package com.codebase.backend.project;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codebase.backend.project.dto.Project;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class ProjectController {

	
	private final ProjectService projectService;
	
	 @GetMapping("/api/store")
	 public List<Project> getProjects() {
	        return projectService.readlist();
	     
	 }
}
