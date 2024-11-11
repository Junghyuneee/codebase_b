package com.codebase.backend.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.codebase.backend.project.dto.Project;

@Mapper
public interface ProjectMapper {
	
	void insertProject(Project project);
	
    List<Project> selectAllProject();
    
    void deleteProjectById(Integer id);
}
