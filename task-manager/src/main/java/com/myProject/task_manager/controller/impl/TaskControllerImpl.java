package com.myProject.task_manager.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/task-management-system")
public class TaskControllerImpl extends BaseController implements ITaskController{

    @Autowired
    ITaskService taskService;

    @PostMapping("/add/task")
    @Override
    public Task addTask(@RequestBody Task task) {
        return taskService.addTask(task);
    }

    @GetMapping("/task-list")
    @Override
    public List<DtoTask> getTaskList() {
        return taskService.getTaskList();
    }

    @PutMapping(path="/task-state-update/{id}")
    @Override
    public Task updateTask(@PathVariable (name = "id") int id, @RequestBody Task task) {
        
        return taskService.updateTask(id,task);
    }

    @GetMapping("/task/{id}")
    @Override
    public RootEntity<DtoTask> getTaskById(@PathVariable Integer id) {
        return ok(taskService.getTaskById(id));
    }


    

    
}
