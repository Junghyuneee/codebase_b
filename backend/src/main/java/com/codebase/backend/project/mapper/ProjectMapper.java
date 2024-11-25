package com.codebase.backend.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.codebase.backend.project.dto.Project;

@Mapper
public interface ProjectMapper {
	
	void insertProject(Project project);
	
    List<Project> selectAllProject();
    
    Project findById(Integer id); //반환타입 리스트아니어도 되는지..? ㅠ
    
    void deleteProjectById(Integer id);
}
