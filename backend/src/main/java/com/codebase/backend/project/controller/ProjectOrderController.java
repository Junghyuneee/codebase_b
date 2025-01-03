package com.codebase.backend.project.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.codebase.backend.project.dto.ProjectOrder;
import com.codebase.backend.project.mapper.ProjectOrderMapper;
import com.codebase.backend.project.service.ProjectOrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ProjectOrderController {
	private final ProjectOrderMapper projectOrderMapper;
 	
	
	@GetMapping("/test/myproject/{id}")
	public List<ProjectOrder> myProject(@PathVariable(value = "id") int id){
		return projectOrderMapper.findByBuyer(id);
	}
	
}
