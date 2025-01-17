package com.myProject.task_manager.services;

import java.util.List;

import com.myProject.task_manager.dto.DtoTask;
import com.myProject.task_manager.dto.DtoTaskIU;

public interface ITaskService {
    
    public DtoTask createTask(DtoTaskIU dtoTaskIU);

    public List<DtoTask> getTaskList();
    
    public DtoTask getTaskById(Integer id);
    
    public DtoTask completeTask(Integer userId,Integer taskId);
    
}
