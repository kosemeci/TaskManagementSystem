package com.myProject.task_manager.dto;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddTasksToProject {

    @NotNull(message="Project id field cannot be empty or null!")
    private Integer projectId;

    @NotEmpty(message="Tasks id field cannot be empty or null!")
    private List<Integer> taskIds;

}