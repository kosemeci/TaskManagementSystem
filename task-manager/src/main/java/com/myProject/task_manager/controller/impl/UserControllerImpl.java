package com.myProject.task_manager.controller.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myProject.task_manager.controller.BaseController;
import com.myProject.task_manager.controller.IUserController;
import com.myProject.task_manager.dto.DtoUser;
import com.myProject.task_manager.dto.DtoUserIU;
import com.myProject.task_manager.entity.RootEntity;
import com.myProject.task_manager.services.IUserService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/user-management/user")
@CrossOrigin(origins = "http://localhost:5173")
public class UserControllerImpl extends BaseController implements IUserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/save")
    @Override
    public DtoUser saveUser(@RequestBody @Valid DtoUserIU dtoUserIU) {
        return userService.saveUser(dtoUserIU);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public List<DtoUser> getUserList() {
        return userService.getUserList();
    }

    @GetMapping("/{id}")
    @Override
    public RootEntity<DtoUser> getUserById(@PathVariable Integer id) {
        return ok(userService.getUserById(id));
    }

    @PutMapping("/change-role/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public RootEntity<DtoUser> changeRole (@PathVariable (name="id") Integer id, @RequestParam String role) {
        return RootEntity.ok(userService.changeRole(id,role));
    }

    @PutMapping("/choose/task")
    @Override
    public RootEntity<DtoUser> chooseTask(@RequestParam Integer taskId) {

        return RootEntity.ok(userService.chooseTask(taskId));
    }
    
    @PutMapping("/update")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public RootEntity<List<DtoUser>> updateUsers(@RequestBody List<DtoUser> dtoUser ) {
        return RootEntity.ok(userService.updateUsers(dtoUser));
    }
           
}