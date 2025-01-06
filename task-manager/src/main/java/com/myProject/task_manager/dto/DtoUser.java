package com.myProject.task_manager.dto;


import java.util.List;

import com.myProject.task_manager.entity.Task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoUser {

    private int id;
    private String firstName;
    private String lastName;
    private String mailAdress;
    private String role;
    private List<Task> task;

}