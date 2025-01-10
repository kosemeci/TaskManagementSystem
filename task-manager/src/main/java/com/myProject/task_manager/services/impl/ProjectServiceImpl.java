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
import com.myProject.task_manager.entity.Project;
import com.myProject.task_manager.entity.Task;
import com.myProject.task_manager.exception.BaseException;
import com.myProject.task_manager.exception.ErrorMessage;
import com.myProject.task_manager.exception.MessageType;
import com.myProject.task_manager.repository.ProjectRepository;
import com.myProject.task_manager.services.IProjectService;

@Service
public class ProjectServiceImpl implements IProjectService{

    @Autowired
    private ProjectRepository projectRepository;
    
    @Override //validasyon işlemleri task ve project için 
    public DtoProject findProjectById(Integer id) { 
        Optional <Project> optional = projectRepository.findById(id);
        if(optional.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NOT_EXIST_PROJECT_RECORD,id.toString()));
        }
        Project dbProject = optional.get();
        DtoProject dtoProject = new DtoProject();
        BeanUtils.copyProperties(dbProject, dtoProject);
        if(dbProject.getTask()!=null){
            List<DtoTask> dtoTaskList = new ArrayList<>();
            for (Task task : dbProject.getTask()) {
                DtoTask dtoTask = new DtoTask();
                DtoUser dtoUser = new DtoUser();
                dtoTask.setId(task.getId());
                dtoTask.setTaskTitle(task.getTaskTitle());
                dtoTask.setDescription(task.getDescription());
                dtoTask.setDeadline(task.getDeadline());
                dtoTask.setAssignedDate(task.getAssignedDate());
                dtoTask.setCompletionDate(task.getCompletionDate());
                dtoTask.setPriority(task.getPriority());
                dtoTask.setStatus(task.getStatus());

                if(task.getUser()!=null){
                    dtoUser.setFirstName(task.getUser().getFirstName());
                    dtoUser.setLastName(task.getUser().getLastName());
                    dtoUser.setMailAdress(task.getUser().getMailAdress());
                    dtoTask.setUser(dtoUser);
                }
                dtoTaskList.add(dtoTask);
            }
            dtoProject.setTask(dtoTaskList);
        }
        return dtoProject;
    } 
}