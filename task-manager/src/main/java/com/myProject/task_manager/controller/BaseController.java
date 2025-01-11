package com.myProject.task_manager.controller;

import org.springframework.web.bind.annotation.RestController;

import com.myProject.task_manager.entity.RootEntity;

@RestController
public class BaseController {
    
    public <T> RootEntity<T> ok(T data){
        return RootEntity.ok(data);
    }

    public <T> RootEntity<T> error(String errorInfo){
        return RootEntity.error(errorInfo);
    }
}
