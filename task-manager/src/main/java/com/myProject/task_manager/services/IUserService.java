package com.myProject.task_manager.services;

import java.util.List;
import java.util.Optional;

import com.myProject.task_manager.dto.DtoUser;
import com.myProject.task_manager.dto.DtoUserIU;
import com.myProject.task_manager.entity.User;

public interface IUserService {

    public DtoUser saveUser(DtoUserIU dtoUserIU);

    public List<User> getUserList();

    public Optional<User> getUserById(int id);
}
