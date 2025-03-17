package com.myProject.task_manager.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myProject.task_manager.controller.BaseController;
import com.myProject.task_manager.controller.ITaskController;
import com.myProject.task_manager.dto.DtoTask;
import com.myProject.task_manager.dto.DtoTaskIU;
import com.myProject.task_manager.entity.RootEntity;
import com.myProject.task_manager.services.ITaskService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/task-management/task")
@CrossOrigin(origins = "http://localhost:5173")
public class TaskControllerImpl extends BaseController implements ITaskController{

    @Autowired
    ITaskService taskService;

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public DtoTask createTask(@RequestBody @Valid DtoTaskIU dtoTaskIU) {
        return taskService.createTask(dtoTaskIU);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @Override
    public List<DtoTask> getTaskList() {
        return taskService.getTaskList();
    }

    @GetMapping("/{id}")
    @Override
    public RootEntity<DtoTask> getTaskById(@PathVariable Integer id) {
        return ok(taskService.getTaskById(id));
    }

    @PutMapping("/complete")
    @PreAuthorize("hasAuthority('USER')")
    @Override
    public RootEntity<DtoTask> completeTask(@RequestParam Integer taskId) {
        // 👇 Kullanıcı bilgilerini JWT'den al
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName(); // Kullanıcının e-posta adresi (JWT içinden)
        
        // Kullanıcıyı veritabanından çek (eğer ID lazımsa)
        Integer userId = taskService.getUserIdByEmail(email); 

        return RootEntity.ok(taskService.completeTask(userId, taskId));
    }

    @DeleteMapping("/delete/{taskId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public String deleteTask(@PathVariable Integer taskId){
        return taskService.deleteTask(taskId);
    }

    @PutMapping("/cancel")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public String cancelTask(@RequestParam Integer taskId) {
        return taskService.cancelTask(taskId);
    }

    @PutMapping("/update/deadline")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public String updateDeadlineTask(@RequestParam Integer taskId , @RequestParam String newDate) {
        return taskService.updateDeadlineTask(taskId,newDate);
    }

    @PutMapping("/assign")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public String assignUserTask(@RequestParam Integer taskId, @RequestParam Integer userId) {
        return taskService.assignUserTask(taskId,userId);
    }
   
}