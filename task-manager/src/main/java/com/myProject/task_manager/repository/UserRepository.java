package com.myProject.task_manager.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myProject.task_manager.model.User;

@Repository
public class UserRepository {

    @Autowired
    private List<User> userList;
    
    public List<User> getAllUsers(){
        return userList;
    }


}
