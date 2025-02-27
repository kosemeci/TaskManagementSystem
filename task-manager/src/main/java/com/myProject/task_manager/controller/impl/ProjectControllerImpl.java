package com.myProject.task_manager.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myProject.task_manager.controller.BaseController;
import com.myProject.task_manager.controller.IProjectController;
import com.myProject.task_manager.dto.AddTasksToProject;
import com.myProject.task_manager.dto.DtoProject;
import com.myProject.task_manager.entity.RootEntity;
import com.myProject.task_manager.services.IProjectService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/project-management/project")
public class ProjectControllerImpl extends BaseController implements IProjectController{

    @Autowired
    private IProjectService projectService;
    
    @GetMapping("/{id}")
    @Override
    public RootEntity<DtoProject> findProjectById(@PathVariable Integer id) {
        return ok(projectService.findProjectById(id));
    }

    @PostMapping("/add/task")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public RootEntity<DtoProject> addTaskToProject(@RequestBody @Valid AddTasksToProject request) {
        return ok(projectService.addTaskToProject(request));
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public RootEntity<DtoProject> createProject(@RequestBody DtoProject project) {
        return ok(projectService.createProject(project));
    }

    @GetMapping()
    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public List<DtoProject> getAllProject() {
        return (projectService.getAllProject());
    }   

}