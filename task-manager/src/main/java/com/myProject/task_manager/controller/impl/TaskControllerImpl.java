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
import org.springframework.web.bind.annotation.RestController;

import com.myProject.task_manager.controller.BaseController;
import com.myProject.task_manager.controller.ITaskController;
import com.myProject.task_manager.dto.DtoTask;
import com.myProject.task_manager.entity.RootEntity;
import com.myProject.task_manager.entity.Task;
import com.myProject.task_manager.services.ITaskService;


@RestController
@RequestMapping("/task-management/task")
public class TaskControllerImpl extends BaseController implements ITaskController{

    @Autowired
    ITaskService taskService;

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public Task addTask(@RequestBody Task task) {
        return taskService.addTask(task);
    }

    @GetMapping("/all")
    @Override
    public List<DtoTask> getTaskList() {
        return taskService.getTaskList();
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public Task updateTask(@PathVariable (name = "id") int id, @RequestBody Task task) {
        return taskService.updateTask(id,task);
    }

    @GetMapping("/{id}")
    @Override
    public RootEntity<DtoTask> getTaskById(@PathVariable Integer id) {
        return ok(taskService.getTaskById(id));
    }
   
}