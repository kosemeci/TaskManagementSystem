package com.myProject.task_manager.controller.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myProject.task_manager.controller.IUserController;
import com.myProject.task_manager.dto.DtoUser;
import com.myProject.task_manager.dto.DtoUserIU;
import com.myProject.task_manager.services.IUserService;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/task-management-system")
public class UserControllerImpl implements IUserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/save")
    @Override
    public DtoUser saveUser(@RequestBody @Valid DtoUserIU dtoUserIU) {
        return userService.saveUser(dtoUserIU);
    }

    @GetMapping("/user-list")
    @Override
    public List<DtoUser> getUserList() {
        return userService.getUserList();
    }

    @GetMapping("/user/{id}")
    @Override
    public DtoUser getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @GetMapping("/tasks/user/{id}")
    @Override
    public DtoUser allTaskOfUserById(@PathVariable int id) {
        return userService.allTaskOfUserById(id);
    }

    // @RequestParam: Sorgulama ve filtreleme için.
    // @PathVariable: Kaynağa doğrudan erişim için.
       
}