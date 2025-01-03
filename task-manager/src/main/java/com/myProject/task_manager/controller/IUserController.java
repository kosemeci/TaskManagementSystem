
package com.myProject.task_manager.controller;

import java.util.List;
import java.util.Optional;

import com.myProject.task_manager.entity.User;

public interface IUserController {

     public User saveUser(User user);

     public List<User> getUserList();

     public Optional<User> getUserById(int id);
}