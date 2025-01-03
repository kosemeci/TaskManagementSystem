package com.myProject.task_manager.dto;


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

}