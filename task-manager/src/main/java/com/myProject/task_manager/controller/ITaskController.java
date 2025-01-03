package com.myProject.task_manager.controller;

import java.util.List;

import com.myProject.task_manager.entity.Task;

public interface ITaskController {

    public Task addTask(Task task);
    public List<Task> getTaskList();
    public Task updateTask(int id,Task task);
    
}
