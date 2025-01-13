package com.codebase.backend.project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.codebase.backend.project.dto.ProjectOrder;
import com.codebase.backend.project.mapper.ProjectOrderMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectOrderService {
	
	private final ProjectOrderMapper projectOrderMapper;
	
	public void save(ProjectOrder projectOrder) {
		projectOrderMapper.save(projectOrder);
	}
	
	List<ProjectOrder> findByBuyer(int buyer_id){
		return projectOrderMapper.findByBuyer(buyer_id);
	}
	

}
