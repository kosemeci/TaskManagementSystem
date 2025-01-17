package com.myProject.task_manager.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public RootEntity<DtoTask> completeTask(@RequestParam Integer userId,@RequestParam Integer taskId) {
        return RootEntity.ok(taskService.completeTask(userId,taskId));
    }
   
}