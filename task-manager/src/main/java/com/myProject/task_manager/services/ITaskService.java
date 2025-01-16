package com.myProject.task_manager.services;

import java.util.List;

import com.myProject.task_manager.dto.DtoTask;
import com.myProject.task_manager.dto.DtoTaskIU;
import com.myProject.task_manager.entity.Task;

public interface ITaskService {
    
    public DtoTask createTask(DtoTaskIU dtoTaskIU);
    public List<DtoTask> getTaskList();
    public Task updateTask(int id,Task task);
    public DtoTask getTaskById(Integer id);
}
