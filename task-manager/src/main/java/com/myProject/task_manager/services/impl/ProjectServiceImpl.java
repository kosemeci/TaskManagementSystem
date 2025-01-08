package com.myProject.task_manager.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myProject.task_manager.dto.DtoProject;
import com.myProject.task_manager.dto.DtoTask;
import com.myProject.task_manager.entity.Project;
import com.myProject.task_manager.entity.Task;
import com.myProject.task_manager.repository.ProjectRepository;
import com.myProject.task_manager.services.IProjectService;

@Service
public class ProjectServiceImpl implements IProjectService{

    @Autowired
    private ProjectRepository projectRepository;
    
    @Override //validasyon işlemleri task ve project için 
    public DtoProject findProjectById(int id) { //buraya taskler içinde user bilgileri de gelmeli , task i user a atama işlemi, taski projeye atma işi 
        Optional <Project> optional = projectRepository.findById(id);
        if(optional.isEmpty()){
            return null;
        }
        Project response = optional.get();
        DtoProject dtoProject = new DtoProject();
        BeanUtils.copyProperties(response, dtoProject);
        if(response.getTask()!=null){
            List<DtoTask> dtoTaskList = new ArrayList<>();
            for (Task task : response.getTask()) {
                DtoTask dtoTask = new DtoTask();
                dtoTask.setId(task.getId());
                dtoTask.setTaskTitle(task.getTaskTitle());
                dtoTask.setDescription(task.getDescription());
                dtoTask.setDeadline(task.getDeadline());
                dtoTask.setAssignedDate(task.getAssignedDate());
                dtoTask.setCompletionDate(task.getCompletionDate());
                dtoTask.setPriority(task.getPriority());
                dtoTask.setStatus(task.getStatus());
                dtoTaskList.add(dtoTask);
            }
            dtoProject.setTask(dtoTaskList);
        }
        return dtoProject;
    } 
}