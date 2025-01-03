package com.myProject.task_manager.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoUserIU {
    
    private String firstName;
    private String lastName;
    private String mailAdress;
    private String role;
    private LocalDate birthOfDate;
    private String telNumber;

}
