package com.codebase.backend.project.mapper;

import java.util.List;

import com.codebase.backend.admin.dto.PopularResponse;
import org.apache.ibatis.annotations.Mapper;

import com.codebase.backend.project.dto.Project;

@Mapper
public interface ProjectMapper {
	
	int insertProject(Project project);
	
    List<Project> selectAllProject();
    
    Project findById(Integer id); //반환타입 리스트아니어도 되는지..? ㅠ

    void incrementHit(Integer id);
    
    void deleteProjectById(Integer id);
    
    List<PopularResponse> findPopularProjects(); // 관리자 페이지 인기 프로젝트 차트에 사용

    List<Project> select5Projects();
    
    void soldout(int id);
}
