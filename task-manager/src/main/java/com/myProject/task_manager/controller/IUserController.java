
package com.myProject.task_manager.controller;

import java.util.List;

import com.myProject.task_manager.dto.DtoUser;
import com.myProject.task_manager.dto.DtoUserIU;

public interface IUserController {

     public DtoUser saveUser(DtoUserIU dtoUserIU);

     public List<DtoUser> getUserList();

     public DtoUser getUserById(int id);

     public DtoUser allTaskOfUserById(int id);
}