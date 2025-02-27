package com.myProject.task_manager.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myProject.task_manager.dto.AddTasksToProject;
import com.myProject.task_manager.dto.DtoProject;
import com.myProject.task_manager.dto.DtoTask;
import com.myProject.task_manager.dto.DtoUser;
import com.myProject.task_manager.entity.Project;
import com.myProject.task_manager.entity.Task;
import com.myProject.task_manager.exception.BaseException;
import com.myProject.task_manager.exception.ErrorMessage;
import com.myProject.task_manager.exception.MessageType;
import com.myProject.task_manager.metrics.CalculateCompletionPercentage;
import com.myProject.task_manager.repository.ProjectRepository;
import com.myProject.task_manager.repository.TaskRepository;
import com.myProject.task_manager.services.IProjectService;

@Service
public class ProjectServiceImpl implements IProjectService{

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private CalculateCompletionPercentage calculate;
    
    @Override
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

    @Override
    public DtoProject addTaskToProject(AddTasksToProject request) {
        
        Integer projectId = request.getProjectId();
        List <Integer> taskIds = request.getTaskIds();

        Project dbProject = projectRepository.findById(projectId).orElseThrow(()->
            new BaseException(new ErrorMessage(MessageType.NOT_EXIST_PROJECT_RECORD,projectId.toString()))
        );

        DtoProject dtoProject = new DtoProject();
        BeanUtils.copyProperties(dbProject, dtoProject);
        List<DtoTask> dtoTaskList = new ArrayList<>();
        if(dbProject.getTask()!=null){
            for (Task currentTask : dbProject.getTask()) {

                DtoTask currentDtoTask = new DtoTask();
                currentDtoTask.setId(currentTask.getId());
                currentDtoTask.setTaskTitle(currentTask.getTaskTitle());
                currentDtoTask.setDescription(currentTask.getDescription());
                currentDtoTask.setCreatedDate(currentTask.getCreatedDate());
                currentDtoTask.setDeadline(currentTask.getDeadline());

                DtoUser currentDtoUser = new DtoUser();
                if(currentTask.getUser()!=null){
                    currentDtoUser.setId(currentTask.getUser().getId());
                    currentDtoUser.setFirstName(currentTask.getUser().getFirstName());
                    currentDtoUser.setLastName(currentTask.getUser().getLastName());
                    currentDtoUser.setMailAdress(currentTask.getUser().getMailAdress());
                    currentDtoTask.setUser(currentDtoUser);
                }
                dtoTaskList.add(currentDtoTask);
            }
            dtoProject.setTask(dtoTaskList);
        }
        if(taskIds!=null){
            for (Integer taskId : taskIds) {
                Task task = taskRepository.findById(taskId).orElseThrow(()->
                    new BaseException(new ErrorMessage(MessageType.NOT_EXIST_TASK_RECORD,taskId.toString()))
                );
                task.setProject(dbProject);
                taskRepository.save(task);
                dbProject.getTask().add(task);
                projectRepository.save(dbProject);
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
                dtoTaskList.add(dtoTask);
            }
            dtoProject.setTask(dtoTaskList);
            dtoProject.setCompletionPercentage(calculate.calculateCompletionPercentage(projectId));
        }
        return dtoProject;
    }

    @Override
    public DtoProject createProject(DtoProject dtoProject) {
        Project newProject = new Project();
        BeanUtils.copyProperties(dtoProject, newProject);
        newProject.setCompletionPercentage(0.00);
        Project dbProject = projectRepository.save(newProject);
        dtoProject.setCreatedTime(dbProject.getCreatedDate());
        dtoProject.setCompletionPercentage(dbProject.getCompletionPercentage());
        return dtoProject; 
    }

    @Override
    public List<DtoProject> getAllProject() {
        List<Project> projectList = projectRepository.findAll();
        List<DtoProject> dtoProjectList = new ArrayList<>();
        for(Project project : projectList){
            List<DtoTask> dtoTaskList = new ArrayList<>();
            DtoProject dtoProject = new DtoProject();
            BeanUtils.copyProperties(project, dtoProject);
            for (Task task : project.getTask()) {
                DtoTask dtoTask = new DtoTask();
                dtoTask.setId(task.getId());//gerek var mı düşün
                dtoTask.setTaskTitle(task.getTaskTitle());
                dtoTask.setStatus(task.getStatus());
                if(task.getUser()!=null){
                    DtoUser dtoUser = new DtoUser();
                    dtoUser.setFirstName(task.getUser().getFirstName());
                    dtoUser.setLastName(task.getUser().getLastName());
                    dtoUser.setMailAdress(task.getUser().getMailAdress());
                    dtoTask.setUser(dtoUser);
                }
                dtoTaskList.add(dtoTask);
            }
            dtoProject.setTask(dtoTaskList);
            dtoProjectList.add(dtoProject);
        }
        return dtoProjectList;
    } 

}