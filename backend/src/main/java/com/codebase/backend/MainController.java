package com.codebase.backend;

import com.codebase.backend.project.dto.Project;
import com.codebase.backend.project.service.ProjectService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/main")
public class MainController {

    @Autowired
    private final ProjectService projectService;

    @GetMapping("/projectCard")
    public List<Project> projectCard() {
        return projectService.select5Projects();
    }

}
