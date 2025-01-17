package com.myProject.task_manager.controller;

import java.util.List;

import com.myProject.task_manager.dto.DtoTask;
import com.myProject.task_manager.dto.DtoTaskIU;
import com.myProject.task_manager.entity.RootEntity;

public interface ITaskController {

    public DtoTask createTask(DtoTaskIU dtoTaskIU);

    public List<DtoTask> getTaskList();

    public RootEntity<DtoTask> getTaskById(Integer id);
    
    public RootEntity<DtoTask> completeTask(Integer userId,Integer taskId);
    
}
