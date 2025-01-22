package com.myProject.task_manager.services;

import com.myProject.task_manager.dto.AddTasksToProject;
import com.myProject.task_manager.dto.DtoProject;
import com.myProject.task_manager.entity.Project;

public interface IProjectService {
    
    public DtoProject findProjectById(Integer id);

    public DtoProject addTaskToProject(AddTasksToProject addTasksToProject);

    public DtoProject createProject(DtoProject project);
}