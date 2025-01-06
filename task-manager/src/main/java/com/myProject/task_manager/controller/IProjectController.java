package com.myProject.task_manager.controller;

import com.myProject.task_manager.entity.Project;

public interface IProjectController {
    
    public Project findTaskById(int id);
}
