package com.myProject.task_manager.controller.impl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myProject.task_manager.controller.IUserController;
import com.myProject.task_manager.entity.User;
import com.myProject.task_manager.services.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/task-management-system")
public class UserControllerImpl implements IUserController {

    @Autowired
    private IUserService iUserService;

    @PostMapping("/save")
    @Override
    public User saveUser(@RequestBody User user) {
        return iUserService.saveUser(user);
    }
    
}
