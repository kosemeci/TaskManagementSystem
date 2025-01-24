package com.myProject.task_manager.entity;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
    private Integer id;

    @Column(name="project_name",nullable=false)
    private String projectName;

    @Column(name="description",nullable=false)
    private String description;

    @OneToMany(mappedBy = "project")
    private List<Task> task;

    @Column(name="created_date")
    @CreationTimestamp
    private LocalDate createdDate;

    @Column(name="completion_percentage")
    private Double completionPercentage=0.00;


}