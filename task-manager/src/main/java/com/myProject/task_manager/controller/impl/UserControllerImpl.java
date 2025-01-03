package com.myProject.task_manager.controller.impl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myProject.task_manager.controller.IUserController;
import com.myProject.task_manager.entity.User;
import com.myProject.task_manager.services.IUserService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/task-management-system")
public class UserControllerImpl implements IUserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/save")
    @Override
    public User saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping("/user-list")
    @Override
    public List<User> getUserList() {
        return userService.getUserList();
    }

    @GetMapping("/user/{id}")
    @Override
    public Optional<User> getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    // @RequestParam: Sorgulama ve filtreleme için.
    // @PathVariable: Kaynağa doğrudan erişim için.
    
    
    
}
