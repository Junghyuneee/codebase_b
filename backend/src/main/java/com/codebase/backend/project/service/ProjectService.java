package com.codebase.backend.project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.codebase.backend.project.dto.Project;
import com.codebase.backend.project.mapper.ProjectMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectService {
	
	private final ProjectMapper projectMapper;
	
	
	public int create(Project project) {
        projectMapper.insertProject(project);
        return project.getId();
    }
	
	public List<Project> readlist(){
		return projectMapper.selectAllProject();
	}
	
	public Project findById(Integer id){ 
		//System.out.print(projectMapper.findById(id)); 
		return projectMapper.findById(id);
	}
	
	public void incrementHit(Integer id) {
		
		projectMapper.incrementHit(id);
	}
	
	public void delete(Integer id) {
		projectMapper.deleteProjectById(id);
	}

	public List<Project> select5Projects() {
		return projectMapper.selectAllProject();
	}
	

}
