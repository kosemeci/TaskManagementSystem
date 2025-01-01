package com.myProject.task_manager.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myProject.task_manager.model.Task;
import com.myProject.task_manager.model.User;

@Repository
public class UserRepository {

    @Autowired
    private List<User> userList;
    @Autowired
    private List<Task> taskList;
    
    public List<User> getAllUsers(){
        return userList;
    }

    public List<Task> getAllTask(){
        return taskList;
    }

}
