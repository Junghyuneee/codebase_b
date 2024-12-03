package com.codebase.backend.admin.controller;

import com.codebase.backend.admin.dto.PopularResponse;
import com.codebase.backend.admin.service.PopularService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dashboard/popular")
public class PopularController {

    @Autowired
    private PopularService popularService;

    @GetMapping("/project")
    public List<PopularResponse> popularProjects() {
        System.out.println("실행O?");
        return popularService.getPopularProjects();
    }

}
