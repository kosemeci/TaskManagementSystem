package com.myProject.task_manager.controller;

import java.util.List;

import com.myProject.task_manager.dto.DtoTask;
import com.myProject.task_manager.dto.DtoTaskIU;
import com.myProject.task_manager.entity.RootEntity;
import com.myProject.task_manager.entity.Task;

public interface ITaskController {

    public DtoTask createTask(DtoTaskIU dtoTaskIU);
    public List<DtoTask> getTaskList();
    public Task updateTask(int id,Task task);
    public RootEntity<DtoTask> getTaskById(Integer id);
    
}
