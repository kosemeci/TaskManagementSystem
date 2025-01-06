package com.myProject.task_manager.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myProject.task_manager.dto.DtoUser;
import com.myProject.task_manager.dto.DtoUserIU;
import com.myProject.task_manager.entity.User;
import com.myProject.task_manager.repository.UserRepository;
import com.myProject.task_manager.services.IUserService;

@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    private UserRepository userRepository;
    
    @Override
    public DtoUser saveUser(DtoUserIU dtoUserIU) {
        DtoUser response = new DtoUser();
        User user = new User();
        BeanUtils.copyProperties(dtoUserIU, user);

        User dbUser = userRepository.save(user);
        BeanUtils.copyProperties(dbUser, response);
        return response;
    }

    @Override
    public List<DtoUser> getUserList() {
        List<User> userList = userRepository.findAll();
        List<DtoUser> dtoUserList = new ArrayList<>();
        for (User user : userList) {
            DtoUser dtoUser = new DtoUser();    
            BeanUtils.copyProperties(user, dtoUser);
            dtoUserList.add(dtoUser);
        }
        return dtoUserList;
    }

    @Override
    public DtoUser getUserById(int id){
        Optional<User> optional = userRepository.findById(id);
        DtoUser dtoUser = new DtoUser();
        if(optional.isPresent()){
            User user = optional.get();
            BeanUtils.copyProperties(user, dtoUser); 
            return dtoUser;
        }
        return null;
    }

    @Override
    public DtoUser allTaskOfUserById(int id) {
        Optional<User> optional = userRepository.findById(id);
        DtoUser dtoUser = new DtoUser();
        if(optional.isPresent()){
            User user = optional.get();
            BeanUtils.copyProperties(user, dtoUser);
            return dtoUser;
        }
        return null;
    }

    
}