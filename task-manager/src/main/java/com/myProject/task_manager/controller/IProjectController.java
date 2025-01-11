package com.myProject.task_manager.controller;

import com.myProject.task_manager.dto.DtoProject;
import com.myProject.task_manager.entity.RootEntity;

public interface IProjectController {
    
    public RootEntity<DtoProject> findProjectById(Integer id);
}