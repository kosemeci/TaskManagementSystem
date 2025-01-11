
package com.myProject.task_manager.controller;

import java.util.List;

import com.myProject.task_manager.dto.DtoUser;
import com.myProject.task_manager.dto.DtoUserIU;
import com.myProject.task_manager.entity.RootEntity;

public interface IUserController {

     public DtoUser saveUser(DtoUserIU dtoUserIU);

     public List<DtoUser> getUserList();

     public RootEntity<DtoUser> getUserById(Integer id);

}