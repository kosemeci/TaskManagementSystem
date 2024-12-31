package com.myProject.task_manager.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.myProject.task_manager.model.User;

@Configuration
public class AppConfig {
    @Bean
    public List<User> userList(){
        List<User> userList = new ArrayList<>();
        userList.add(new User(1,"Alperen","ksmci@gmail.com"));
        userList.add(new User(2,"Engin","Dere@gmail.com"));
        userList.add(new User(3,"Ahmet","Demir@gmail.com"));
        userList.add(new User(3,"Yusuf","sinyorfc@gmail.com"));
        return userList;
    }
}
