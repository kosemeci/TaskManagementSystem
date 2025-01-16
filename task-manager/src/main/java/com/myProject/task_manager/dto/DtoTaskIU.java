package com.myProject.task_manager.dto;

import java.time.LocalDate;

import com.myProject.task_manager.entity.Priority;
import com.myProject.task_manager.entity.Status;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoTaskIU {
    @NotEmpty(message="Task title field cannot be empty or null!")
    private String taskTitle;

    @NotEmpty(message="Description field cannot be empty or null!")
    private String description;

    @NotEmpty(message="Priority field cannot be empty or null!")
    @Pattern(regexp = "^(LOW|MEDIUM|HIGH)$", message = "Priority field must be one of: LOW, MEDIUM, HIGH")
    private Priority priority;
    
    private Status status=Status.PENDING;
    private LocalDate deadline ;
}
