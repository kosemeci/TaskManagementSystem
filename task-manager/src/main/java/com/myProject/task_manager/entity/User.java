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
@Table(name="User")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name",nullable = false,length = 30)
    private String firstName;

    @Column(name ="last_name",nullable = false,length = 30)
    private String lastName;

    @Column(name = "mail_adress",nullable = false,unique = true)
    private String mailAdress;

    @Column(name = "role",nullable = false)
    private String role;

}
