package com.myProject.task_manager.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myProject.task_manager.model.Task;
import com.myProject.task_manager.model.User;
import com.myProject.task_manager.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.getAllUsers();
    }

    public List<Task> getAllTask(){
        return userRepository.getAllTask();
    }
}
