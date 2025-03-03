package com.myProject.task_manager.services;

import java.util.List;

import com.myProject.task_manager.dto.DtoUser;
import com.myProject.task_manager.dto.DtoUserIU;

public interface IUserService {

    public DtoUser saveUser(DtoUserIU dtoUserIU);

    public List<DtoUser> getUserList();

    public DtoUser getUserById(Integer id);

    public DtoUser changeRole(Integer id,String role);

    public DtoUser chooseTask(Integer taskId);

    public List<DtoUser> updateUsers (List<DtoUser> dtoUser);

}
