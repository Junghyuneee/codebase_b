package com.codebase.backend.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.codebase.backend.project.dto.ProjectOrder;

@Mapper
public interface ProjectOrderMapper {
	void save(ProjectOrder projectOrder);
	List<ProjectOrder>findByBuyer(int buyer_id);
	void deleteById(int id);
	ProjectOrder get();
	ProjectOrder copyProject(int project_id);
	ProjectOrder findById(@Param("id") int id);
	boolean existCheck(@Param("buyer_id") int buyer_Id, @Param("project_id") int project_Id);
	
}
