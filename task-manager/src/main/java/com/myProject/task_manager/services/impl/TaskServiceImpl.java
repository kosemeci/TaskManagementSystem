package com.myProject.task_manager.services.impl;

import java.util.List;
import java.util.Optional;
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

    @Override
    public Task updateTask(int id, Task task) {
        
        Optional<Task> optional = taskRepository.findById(id);
        // Task newTask = new Task();
        if(optional.isPresent()){
            // BeanUtils.copyProperties(optional, newTask);
            Task newTask = optional.get();
            newTask.setStatus(task.getStatus());
            newTask.setCompletionDate(task.getCompletionDate());
            return taskRepository.save(newTask);
        }
        return null;
        
    }
    
}
