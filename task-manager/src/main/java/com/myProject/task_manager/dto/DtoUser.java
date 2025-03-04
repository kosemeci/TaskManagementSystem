package com.myProject.task_manager.dto;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.myProject.task_manager.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value=Include.NON_NULL)
public class DtoUser {

    private Integer id;
    private String firstName;
    private String lastName;
    private String mailAdress;
    private String telNumber;
    private String position;
    private Role role;
    private List<DtoTask> task;

}