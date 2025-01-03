package com.myProject.task_manager.services;

import java.util.List;

import com.myProject.task_manager.entity.Task;

public interface ITaskService {
    
    public Task addTask(Task task);
    public List<Task> getTaskList();
}
