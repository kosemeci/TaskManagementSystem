package com.myProject.task_manager.services;

import java.util.List;

import com.myProject.task_manager.dto.AddTasksToProject;
import com.myProject.task_manager.dto.DtoProject;

public interface IProjectService {
    
    public DtoProject findProjectById(Integer id);

    public DtoProject addTaskToProject(AddTasksToProject addTasksToProject);

    public DtoProject createProject(DtoProject project);

    public List<DtoProject> getAllProject();

}