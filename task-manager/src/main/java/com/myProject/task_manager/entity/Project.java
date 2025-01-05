package com.myProject.task_manager.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="project")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int is;

    @Column(name="project_name",nullable=false)
    private String projectName;

    @Column(name="description")
    private String description;

}
