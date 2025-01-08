package com.myProject.task_manager.controller;

import com.myProject.task_manager.dto.DtoProject;

public interface IProjectController {
    
    public DtoProject findProjectById(int id);
}