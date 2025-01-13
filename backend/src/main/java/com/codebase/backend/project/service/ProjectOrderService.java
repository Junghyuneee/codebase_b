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
	
	public List<ProjectOrder> findByBuyer(int buyer_id){
		return projectOrderMapper.findByBuyer(buyer_id);
	}
	
	public boolean existCheck(int buyer_id, int project_id) {
		return projectOrderMapper.existCheck(buyer_id, project_id);
	}
}
