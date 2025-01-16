package com.myProject.task_manager.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.myProject.task_manager.entity.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value=Include.NON_NULL)
public class DtoTask {

    private int id;
    private String taskTitle;
    private String description;
    private String priority;    
    private Status status=Status.PENDING;
    private LocalDate createdDate;
    private LocalDate assignedDate ;
    private LocalDate completionDate ;
    private LocalDate deadline ;
    private DtoUser user;
    private DtoProject project;

}