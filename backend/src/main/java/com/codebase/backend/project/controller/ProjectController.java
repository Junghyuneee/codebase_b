package com.codebase.backend.project.controller;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.codebase.backend.configs.S3Service;
import com.codebase.backend.member.dto.Member;
import com.codebase.backend.member.service.JwtService;
import com.codebase.backend.project.dto.ProjectOrder;
import com.codebase.backend.project.dto.Payment;
import com.codebase.backend.project.dto.Project;
import com.codebase.backend.project.service.ProjectService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:5173" })
public class ProjectController {

	 
    
    private final S3Service s3Service;
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
		System.out.println("DETAIL PAGE REQUEST");
//		//요청확인용
//		Enumeration<String> headerNames = request.getHeaderNames();
//        while (headerNames.hasMoreElements()) {
//            String headerName = headerNames.nextElement();
//            String headerValue = request.getHeader(headerName);
//            System.out.println(headerName + ": " + headerValue); // 헤더 이름과 값 출력
//        }
		
		projectService.incrementHit(id); // 죄다 두번 돌ㅇㅏ가네 아... 어쨰서ㅠ 
		return projectService.findById(id);
	}

	@PatchMapping("/api/store/{id}/hit")
	public void projectPlusHit() {
		
	}
	
	
	// 프로젝트 생성
	@PostMapping("/api/store/add")
	public ResponseEntity<String> postTest(Project p,
			@RequestParam("file") MultipartFile file, @AuthenticationPrincipal Member user) throws IOException {

		System.out.println(p.toString());
		System.out.println("File Name: " + file.getOriginalFilename());
		System.out.println("File Size: " + file.getSize() + " bytes");
		System.out.println("File Type: " + file.getContentType());
		System.out.println("user = " + user);
		
		
		if (file != null && !file.isEmpty()) {
			//String encodedString = URLEncoder.encode( , "UTF-8");
            String fileName = UUID.randomUUID() + file.getOriginalFilename();
            //s3Service.uploadFile(file, fileName);
            fileName = "b62ea3b9-6b5f-4498-8b22-281a13d0c873mm.png";
            p.setImg(fileName);
        }
		
		
		p.setUsername(user.getName());
		p.setMaker_id(user.getId());
		projectService.create(p);
		
		
		return ResponseEntity.ok(p.getId()+"");
	}



	@DeleteMapping("/api/store/delete")
	public ResponseEntity<String> delete(@RequestBody int project_id){
		System.out.println(project_id);
		return ResponseEntity.ok("");
	}
	
	
	@DeleteMapping("/api/project/delete/{id}")
    public ResponseEntity<String> deleteProject(@PathVariable("id") int id, @AuthenticationPrincipal Member user) {
        Project p = projectService.findById(id);
        System.out.println(user.toString()+ " " + p.toString());
		
		if (p.getMaker_id() == user.getId()) {
			projectService.delete(id);
            return ResponseEntity.ok(p.getTitle() + "가 삭제되었습니다");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("권한없음");
        }
    }
	
}
