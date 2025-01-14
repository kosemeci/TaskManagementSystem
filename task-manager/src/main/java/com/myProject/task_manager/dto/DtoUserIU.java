package com.myProject.task_manager.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoUserIU {
    
    @NotEmpty(message="Firstname field cannot be empty or null!")
    @Size(min=3,max=25,message="Firstname field must be min 3 digits!")
    private String firstName;

    @NotEmpty(message="Lastname field cannot be empty or null!")
    @Size(min=2,max=30,message="Lastname field must be min 2 digits!")
    private String lastName;

    @NotEmpty(message="Mail address field cannot be empty or null!")
    @Email(message="Enter a valid mail format!")
    private String mailAdress;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&.])[A-Za-z\\d@$!%*?&.]{6,20}$", 
         message = "Password must contain at least one letter, one number, and one special character")
    private String password;

    @NotEmpty(message="Position field cannot be empty or null!")
    private String position;

    @NotNull(message="Birth of date field cannot be null!")
    private LocalDate birthOfDate;

    @NotEmpty(message="Telephone number field cannot be empty or null!")
    @Size(min = 11, max = 11,message="Telephone number field must be 11 digits!")
    private String telNumber;

}
