package com.codebase.backend.project.controller;

import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
		//System.out.println(projectService.readlist());
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
	

	@PostMapping("/api/store")
	public ResponseEntity<String> postTest(@RequestBody Map<String, Object> data, @AuthenticationPrincipal Member user, HttpServletRequest request) {
		
		
		System.out.println(data.toString());
		//System.out.println(user.toString());
		
//		//요청확인용
//		Enumeration<String> headerNames = request.getHeaderNames();
//        while (headerNames.hasMoreElements()) {
//            String headerName = headerNames.nextElement();
//            String headerValue = request.getHeader(headerName);
//            System.out.println(headerName + ": " + headerValue); // 헤더 이름과 값 출력
//        }
//
//	    // Authorization 헤더에서 토큰 추출
//	    String authHeader = request.getHeader("Authorization");
//	    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//	        throw new RuntimeException("Missing or invalid Authorization header.");
//	    }
//	    String token = authHeader.substring(7); // "Bearer " 이후의 토큰 추출
//		System.out.println(token);
//		if(user == null) {
//			System.out.println("null임");
//			return ResponseEntity.ok("string---");
//		}
		System.out.println("user = " + user);
		
		return ResponseEntity.ok("string---");
	}
}
