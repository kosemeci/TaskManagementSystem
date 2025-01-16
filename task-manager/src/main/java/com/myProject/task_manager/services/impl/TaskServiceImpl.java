package com.myProject.task_manager.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myProject.task_manager.dto.DtoProject;
import com.myProject.task_manager.dto.DtoTask;
import com.myProject.task_manager.dto.DtoUser;
import com.myProject.task_manager.entity.Task;
import com.myProject.task_manager.exception.BaseException;
import com.myProject.task_manager.exception.ErrorMessage;
import com.myProject.task_manager.exception.MessageType;
import com.myProject.task_manager.repository.TaskRepository;
import com.myProject.task_manager.services.ITaskService;

@Service
public class TaskServiceImpl implements ITaskService{

    @Autowired
    TaskRepository taskRepository;

    @Override
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public List<DtoTask> getTaskList() {

        List <Task> dbTask = taskRepository.findAll();
        List <DtoTask> responseDtoTask = new ArrayList<>(); 
        for (Task task : dbTask) {
            DtoTask dtoTask = new DtoTask();
            BeanUtils.copyProperties(task, dtoTask);

            if(task.getUser()!=null){
                DtoUser dtoUser = new DtoUser();
                dtoUser.setId(task.getUser().getId());
                dtoUser.setFirstName(task.getUser().getFirstName());
                dtoUser.setLastName(task.getUser().getLastName());
                dtoUser.setMailAdress(task.getUser().getMailAdress());
                dtoTask.setUser(dtoUser);
            }
            if(task.getProject()!=null){
                DtoProject dtoProject = new DtoProject();
                dtoProject.setId(task.getProject().getId());
                dtoProject.setProjectName(task.getProject().getProjectName());
                dtoProject.setDescription(task.getProject().getDescription());
                dtoTask.setProject(dtoProject);
            }
            responseDtoTask.add(dtoTask);
        }
        return responseDtoTask;
    }

    @Override
    public Task updateTask(int id, Task task) {
        
        Optional<Task> optional = taskRepository.findById(id);
        if(optional.isPresent()){
            Task newTask = optional.get();
            newTask.setStatus(task.getStatus());
            newTask.setCompletionDate(task.getCompletionDate());
            return taskRepository.save(newTask);
        }
        return null;
        
    }

    @Override
    public DtoTask getTaskById(Integer id) {
        Optional<Task> optional = taskRepository.findById(id);
        DtoTask dtoTask = new DtoTask();
        if(optional.isPresent()){
            Task task = optional.get();
            DtoUser dtoUser = new DtoUser();
            BeanUtils.copyProperties(task, dtoTask);
            if(task.getUser()!=null){
                dtoUser.setId(task.getUser().getId());
                dtoUser.setFirstName(task.getUser().getFirstName());
                dtoUser.setLastName(task.getUser().getLastName());
                dtoUser.setMailAdress(task.getUser().getMailAdress());
                dtoTask.setUser(dtoUser);
            }
            if(task.getProject()!=null){
                DtoProject dtoProject = new DtoProject();
                dtoProject.setId(task.getProject().getId());
                dtoProject.setProjectName(task.getProject().getProjectName());
                dtoProject.setDescription(task.getProject().getDescription());
                dtoTask.setProject(dtoProject);
            }
            return dtoTask; 
        }
        throw new BaseException(new ErrorMessage(MessageType.NOT_EXIST_TASK_RECORD,id.toString()));
    }
}