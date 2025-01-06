package com.myProject.task_manager.services;

import com.myProject.task_manager.entity.Project;

public interface IProjectService {
    
    public Project findTaskById(int id);
}
