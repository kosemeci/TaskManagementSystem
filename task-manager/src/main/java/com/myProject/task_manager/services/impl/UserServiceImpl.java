package com.myProject.task_manager.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myProject.task_manager.entity.User;
import com.myProject.task_manager.repository.IUserRepository;
import com.myProject.task_manager.services.IUserService;

@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    private IUserRepository userRepository;
    
    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getUserList() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(int id){
        return userRepository.findById(id);
    }

    
}