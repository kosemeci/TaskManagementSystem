package com.myProject.task_manager.controller;

import java.util.List;

import com.myProject.task_manager.dto.DtoTask;
import com.myProject.task_manager.dto.DtoTaskIU;
import com.myProject.task_manager.entity.RootEntity;

public interface ITaskController {

    public DtoTask createTask(DtoTaskIU dtoTaskIU);

    public List<DtoTask> getTaskList();

    public RootEntity<DtoTask> getTaskById(Integer id);
    
    public RootEntity<DtoTask> completeTask(Integer taskId);

    public String deleteTask(Integer taskId);

    public String cancelTask(Integer taskId);

    public String updateDeadlineTask(Integer taskId, String date);

    public String assignUserTask(Integer taskId,Integer userId);
   
}