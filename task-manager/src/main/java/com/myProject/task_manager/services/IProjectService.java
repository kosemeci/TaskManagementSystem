package com.myProject.task_manager.services;

import com.myProject.task_manager.dto.DtoProject;

public interface IProjectService {
    
    public DtoProject findProjectById(int id);
}