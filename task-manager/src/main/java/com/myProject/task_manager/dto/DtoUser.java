package com.myProject.task_manager.dto;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value=Include.NON_NULL)
public class DtoUser {

    private int id;
    private String firstName;
    private String lastName;
    private String mailAdress;
    private String role;
    private List<DtoTask> task;//BURADA KALDIN BURAYI DTO TASK YAP! YAPMASANDA OLUR AMA YAPSAN DAHA GÜZEL OLUCAK

}