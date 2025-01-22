package com.myProject.task_manager.services;

import com.myProject.task_manager.dto.AddTasksToProject;
import com.myProject.task_manager.dto.DtoProject;

public interface IProjectService {
    
    public DtoProject findProjectById(Integer id);

    public DtoProject addTaskToProject(AddTasksToProject addTasksToProject);
}