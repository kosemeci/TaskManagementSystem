package com.myProject.task_manager.controller;

import java.util.List;

import com.myProject.task_manager.dto.AddTasksToProject;
import com.myProject.task_manager.dto.DtoProject;
import com.myProject.task_manager.entity.RootEntity;

public interface IProjectController {
    
    public RootEntity<DtoProject> findProjectById(Integer id);

    public RootEntity<DtoProject> addTaskToProject(AddTasksToProject addTasksToProject);

    public RootEntity<DtoProject> createProject(DtoProject project); 

    public List<DtoProject> getAllProject();
}