package com.myProject.task_manager.services;

import java.util.List;

import com.myProject.task_manager.dto.DtoTask;
import com.myProject.task_manager.dto.DtoTaskIU;

public interface ITaskService {
    
    public DtoTask createTask(DtoTaskIU dtoTaskIU);

    public List<DtoTask> getTaskList();
    
    public DtoTask getTaskById(Integer id);
    
    public DtoTask completeTask(Integer userId,Integer taskId);

    public Integer getUserIdByEmail(String email);

    public String deleteTask(Integer taskId);

    public String cancelTask(Integer taskId);

    public String updateDeadlineTask(Integer taskId,String date);

    public String assignUserTask(Integer taskId,Integer userId);
}
