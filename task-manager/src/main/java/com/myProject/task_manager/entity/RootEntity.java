package com.myProject.task_manager.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RootEntity<T> {
    
    private Boolean status;
    private String errorMessage;
    private T data;

    public static <T> RootEntity <T> ok(T data){
        RootEntity<T> rootEntity = new RootEntity<>();
        rootEntity.setStatus(true);
        rootEntity.setErrorMessage(null);
        rootEntity.setData(data);
        return rootEntity;
    }

    public static <T> RootEntity<T> error(String errorInfo){
        RootEntity<T> rootEntity = new RootEntity<>();
        rootEntity.setData(null);
        rootEntity.setErrorMessage(errorInfo);
        rootEntity.setStatus(false);
        return rootEntity;
    }

}