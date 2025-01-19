package com.codebase.backend.project.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codebase.backend.member.dto.Member;
import com.codebase.backend.project.dto.ProjectOrder;
import com.codebase.backend.project.mapper.ProjectOrderMapper;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/u/store/projectorder")
public class ProjectOrderController {
	private final ProjectOrderMapper projectOrderMapper;
 	
	//구매한 프로젝트
//	@GetMapping("/test/myproject/{id}")
//	public List<ProjectOrder> myProject(@PathVariable(value = "id") int id){
//		return projectOrderMapper.findByBuyer(id);
//	}
	
	@GetMapping("/myproject")
	public List<ProjectOrder> myProject(@AuthenticationPrincipal Member user){
		System.out.println("asdfasfasdfasdf " + user.getId());
		System.out.println( projectOrderMapper.findByBuyer(user.getId()));
		return projectOrderMapper.findByBuyer(user.getId());
	}
	
//	@GetMapping("/myproject/{user_id}")
//	public List<ProjectOrder> myProject(@AuthenticationPrincipal Member user, @PathVariable("user_id") int id){
//		//System.out.println("asdfasfasdfasdf " + user.getId());
//		System.out.println("userid : " + id);
//		return projectOrderMapper.findByBuyer(id);
//	}
	
	
	@GetMapping("/myproject/{id}")
	public ProjectOrder projectOrder(@AuthenticationPrincipal Member user, @PathVariable("id") int id) {
		System.out.println(projectOrderMapper.findById(id));
		return projectOrderMapper.findById(id);
	}
	
	
	
}
