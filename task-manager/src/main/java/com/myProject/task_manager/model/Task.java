package com.myProject.task_manager.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    private int id;
    private String description;
    private Boolean status;
    private String end_of_time;
    private String begin_of_time;
}
