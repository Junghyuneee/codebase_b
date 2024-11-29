package com.codebase.backend.project.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.codebase.backend.member.dto.Member;
import com.codebase.backend.member.service.JwtService;
import com.codebase.backend.project.dto.Project;
import com.codebase.backend.project.service.ProjectService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:5173" })
public class ProjectController {

	private final ProjectService projectService;
	private final JwtService jwtService;

	@GetMapping("/api/store")
	@ResponseBody
	public List<Project> getProjects() {
		 System.out.println(projectService.readlist());
		return projectService.readlist();

	}

	@GetMapping("/api/store/{id}")
	@ResponseBody
	public Project projectFindById(@PathVariable("id") Integer id, HttpServletRequest request) {

//		//요청확인용
//		Enumeration<String> headerNames = request.getHeaderNames();
//        while (headerNames.hasMoreElements()) {
//            String headerName = headerNames.nextElement();
//            String headerValue = request.getHeader(headerName);
//            System.out.println(headerName + ": " + headerValue); // 헤더 이름과 값 출력
//        }

		return projectService.findById(id);
	}

	// 프로젝트 생성
	@PostMapping("/api/store/add")
	public ResponseEntity<String> postTest(Project p,
			@RequestParam("file") MultipartFile file, @AuthenticationPrincipal Member user) {

		System.out.println(p.toString());
		System.out.println("File Name: " + file.getOriginalFilename());
		System.out.println("File Size: " + file.getSize() + " bytes");
		System.out.println("File Type: " + file.getContentType());
		System.out.println("user = " + user);
		
		//
		
		p.setUsername(user.getName());
		p.setMaker_id(user.getId());
		projectService.create(p);
		
		return ResponseEntity.ok("string---");
	}
}
