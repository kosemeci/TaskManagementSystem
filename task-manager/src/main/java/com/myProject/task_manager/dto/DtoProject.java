package com.myProject.task_manager.dto;

import java.time.LocalDateTime;
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
public class DtoProject {
    
    private Integer id;
    private String projectName;
    private String description;
    private LocalDateTime createdTime;
    private Double completionPercentage;
    private List<DtoTask> task;
    
}
