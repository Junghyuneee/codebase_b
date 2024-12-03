package com.codebase.backend.admin.service;

import com.codebase.backend.admin.dto.PopularResponse;
import com.codebase.backend.project.mapper.ProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PopularService {

    @Autowired
    private ProjectMapper projectMapper;

    public List<PopularResponse> getPopularProjects() {
        return projectMapper.findPopularProjects();
    }

}
