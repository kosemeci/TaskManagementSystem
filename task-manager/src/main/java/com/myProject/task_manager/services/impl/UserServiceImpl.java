package com.myProject.task_manager.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myProject.task_manager.entity.User;
import com.myProject.task_manager.repository.IUserRepository;
import com.myProject.task_manager.services.IUserService;

@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    private IUserRepository iUserRepository;
    
    @Override
    public User saveUser(User user) {
        return iUserRepository.save(user);
    }
    
}