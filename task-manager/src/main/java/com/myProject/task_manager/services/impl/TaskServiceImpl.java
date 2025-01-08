package com.myProject.task_manager.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myProject.task_manager.dto.DtoTask;
import com.myProject.task_manager.dto.DtoUser;
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
    public List<DtoTask> getTaskList() {

        List <Task> dbTask = taskRepository.findAll();
        List <DtoTask> responseDtoTask = new ArrayList<>(); 
        for (Task task : dbTask) {
            DtoTask dtoTask = new DtoTask();
            BeanUtils.copyProperties(task, dtoTask);

            if(task.getUser()!=null){
                DtoUser dtoUser = new DtoUser();
                BeanUtils.copyProperties(task.getUser(), dtoUser);
                //dtoTask.setUser(dtoUser);
            }
            responseDtoTask.add(dtoTask);

        }
        return responseDtoTask;
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

    @Override
    public DtoTask getTaskById(int id) {
        Optional<Task> optional = taskRepository.findById(id);
        DtoTask dtoTask = new DtoTask();
        if(optional.isPresent()){
            Task task = optional.get();
            //User user = optional.get().getUser();
            DtoUser dtoUser = new DtoUser();
            //BeanUtils.copyProperties(user,dtoUser);
            BeanUtils.copyProperties(task, dtoTask);
            //dtoTask.setUser(dtoUser);
            dtoUser.setId(task.getUser().getId());
            dtoUser.setFirstName(task.getUser().getFirstName());
            dtoUser.setLastName(task.getUser().getLastName());
            dtoUser.setMailAdress(task.getUser().getMailAdress());
            dtoTask.setUser(dtoUser);
            return dtoTask; 
        }
        return null;
    }
    
}
