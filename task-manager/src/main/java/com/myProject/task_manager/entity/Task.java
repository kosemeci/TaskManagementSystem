package com.myProject.task_manager.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "task_title",nullable = false)
    private String taskTitle;

    @Column(name = "description",columnDefinition = "VARCHAR(255)",nullable = false)
    private String description;

    @Column(name = "priority",nullable = false)
    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Column(name= "status",nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status=Status.PENDING;

    @Column(name="assigned_date",nullable = true)
    private LocalDate assignedDate ;

    @Column(name="completion_date",nullable = true)
    private LocalDate completionDate ;

    @Column(name = "deadline",nullable = true)
    private LocalDate deadline ;

    @ManyToOne
    @JsonBackReference
    private User user;

    @ManyToOne
    private Project project;

}