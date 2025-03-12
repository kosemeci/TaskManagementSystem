package com.myProject.task_manager.services.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.myProject.task_manager.dto.DtoProject;
import com.myProject.task_manager.dto.DtoTask;
import com.myProject.task_manager.dto.DtoTaskIU;
import com.myProject.task_manager.dto.DtoUser;
import com.myProject.task_manager.entity.Project;
import com.myProject.task_manager.entity.Status;
import com.myProject.task_manager.entity.Task;
import com.myProject.task_manager.entity.User;
import com.myProject.task_manager.exception.BaseException;
import com.myProject.task_manager.exception.ErrorMessage;
import com.myProject.task_manager.exception.MessageType;
import com.myProject.task_manager.metrics.CalculateCompletionPercentage;
import com.myProject.task_manager.repository.ProjectRepository;
import com.myProject.task_manager.repository.TaskRepository;
import com.myProject.task_manager.repository.UserRepository;
import com.myProject.task_manager.services.ITaskService;

@Service
public class TaskServiceImpl implements ITaskService{

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    CalculateCompletionPercentage calculate;

    @Override
    public DtoTask createTask(DtoTaskIU dtoTaskIU) {
        DtoTask dtoTask = new DtoTask();
        DtoProject dtoProject = new DtoProject();
        Task task = new Task();
        BeanUtils.copyProperties(dtoTaskIU, task);

        Project dbProject = projectRepository.findById(dtoTaskIU.getProjectId())
            .orElseThrow(()-> new BaseException(new ErrorMessage(MessageType.NOT_EXIST_PROJECT_RECORD,dtoTaskIU.getProjectId().toString())));
        task.setProject(dbProject);
        
        Task dbTask = taskRepository.save(task);
        dtoProject.setId(dbProject.getId());
        dtoProject.setProjectName(dbProject.getProjectName());
        dtoTask.setProject(dtoProject);
        BeanUtils.copyProperties(dbTask, dtoTask);
        return dtoTask;
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

    @Override
    public DtoTask completeTask(Integer userId, Integer taskId) {
        User user = userRepository.findById(userId).orElseThrow(
            ()-> new BaseException(new ErrorMessage(MessageType.NOT_EXIST_USER_RECORD,userId.toString())));
        Task task = taskRepository.findById(taskId).orElseThrow(
            ()-> new BaseException(new ErrorMessage(MessageType.NOT_EXIST_USER_RECORD,taskId.toString())));
        String ownerTask = task.getUser().getMailAdress();
        // String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        DtoTask dtoTask = new DtoTask();
        if(task.getCompletionDate()==null && user.getMailAdress().equals(ownerTask)){
            task.setStatus(Status.COMPLETED);
            task.setCompletionDate(LocalDate.now());
            taskRepository.save(task);
            double newCompletionPercentage = calculate.calculateCompletionPercentage(task.getProject().getId());
            BeanUtils.copyProperties(task, dtoTask);
            DtoUser dtoUser = new DtoUser();
            dtoUser.setId(user.getId());
            dtoUser.setFirstName(user.getFirstName());
            dtoUser.setLastName(user.getLastName());
            dtoUser.setMailAdress(user.getMailAdress());
            dtoTask.setUser(dtoUser);

            DtoProject dtoProject = new DtoProject();
            Project project = task.getProject();
            project.setCompletionPercentage(newCompletionPercentage);
            BeanUtils.copyProperties(project, dtoProject);
            dtoTask.setProject(dtoProject);
            return dtoTask;
        }
        throw new BaseException(new ErrorMessage(MessageType.UNAUTHORIZED_ACCESS,null));
    }

    @Override
    public Integer getUserIdByEmail(String email) {
        return userRepository.findByMailAdress(email)
                .map(User::getId)
                .orElseThrow(() -> new UsernameNotFoundException("Kullanıcı bulunamadı"));
    }

    @Override
    public String deleteTask(Integer taskId) {
        Task task = taskRepository.findById(taskId)
        .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NOT_EXIST_TASK_RECORD, taskId.toString())));

        taskRepository.delete(task);
        return "The Task has been deleted successfully.";
    }
}