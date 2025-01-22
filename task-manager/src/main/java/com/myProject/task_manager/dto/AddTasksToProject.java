package com.myProject.task_manager.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddTasksToProject {

    private Integer projectId;
    private List<Integer> taskIds;

}