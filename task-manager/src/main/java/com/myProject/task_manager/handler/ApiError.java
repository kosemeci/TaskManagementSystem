package com.myProject.task_manager.handler;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiError<T> {
    
    private String id;
    private Date errorTime;
    private String path;
    private T errors;

}
