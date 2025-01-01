package com.myProject.task_manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myProject.task_manager.model.Task;
import com.myProject.task_manager.model.User;
import com.myProject.task_manager.services.UserService;

@RestController
public class RestUserController {

    @Autowired
    private UserService userService;
    
    @GetMapping("/user-list")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/task-list")
    public List<Task> getAllTask(){
        return userService.getAllTask();
    }
}
