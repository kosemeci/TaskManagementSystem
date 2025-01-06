package com.myProject.task_manager.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myProject.task_manager.controller.IProjectController;
import com.myProject.task_manager.entity.Project;
import com.myProject.task_manager.services.IProjectService;

@RestController
@RequestMapping("/task-management-system")
public class ProjectControllerImpl implements IProjectController{

    @Autowired
    private IProjectService projectService;
    
    @GetMapping("/tasks/project/{id}")
    @Override
    public Project findTaskById(@PathVariable int id) {
        return projectService.findTaskById(id);
    }
    
}
