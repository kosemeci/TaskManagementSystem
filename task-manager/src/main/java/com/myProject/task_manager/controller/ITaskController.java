package com.myProject.task_manager.controller;

import java.util.List;

import com.myProject.task_manager.dto.DtoTask;
import com.myProject.task_manager.entity.Task;

public interface ITaskController {

    public Task addTask(Task task);
    public List<DtoTask> getTaskList();
    public Task updateTask(int id,Task task);
    public DtoTask getTaskById(int id);
    
}
