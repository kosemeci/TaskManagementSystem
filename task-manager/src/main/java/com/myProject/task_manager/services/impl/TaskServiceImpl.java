package com.myProject.task_manager.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myProject.task_manager.entity.Task;
import com.myProject.task_manager.repository.TaskRepository;
import com.myProject.task_manager.services.ITaskService;

@Service
public class TaskServiceImpl implements ITaskService{

    @Autowired
    TaskRepository taskRepository;

    @Override
    public Task addTask(Task task) {
        
        return taskRepository.save(task);
    }

    @Override
    public List<Task> getTaskList() {
        
        return taskRepository.findAll();
    }
    
}
