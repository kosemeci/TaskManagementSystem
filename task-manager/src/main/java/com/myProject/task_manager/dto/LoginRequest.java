package com.myProject.task_manager.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    
    @NotEmpty(message="Mail address field cannot be empty or null!")
    private String mailAdress;

    @NotEmpty(message="Password field cannot be empty or null!")
    private String password;
}
