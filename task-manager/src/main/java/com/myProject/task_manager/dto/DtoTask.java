package com.myProject.task_manager.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoTask {

    private int id;
    private String taskTitle;
    private String description;
    private String priority = "Medium";
    private String status="Pending";
    private LocalDate assignedDate ;
    private LocalDate completionDate ;
    private LocalDate deadline ;
    private DtoUser user;
}
