package com.example.day25lab7lms.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Instructor {
    @NotEmpty(message = "id cannot be empty")
    @Size(min = 5,message = "id cannot be less then 5")
    private String instructorId;

    @NotEmpty(message = "name cannot be empty")
    @Size(min = 3,max = 20, message = "name must be between 3 and 20 characters")
    private String instructorName;

    @NotEmpty (message = "instructor MUST have a classroom")
    @Pattern(regexp = "^[C-Z][0-9]{2}$",message = "Office should start with an uppercase (C-Z)" +
            " followed by two digits (e.g, C23 , D25 ..")
    private String instructorOffice;

    @NotEmpty(message = "field of study cannot be empty")
    private String fieldOfStudy;

    private boolean isOnVacation;

    @NotNull(message = "leave balance cannot be initiated with null")
    @Min(value = 1, message = "leave balance cannot be less than 1 day")
    @Max(value = 30,message = "leave balance cannot be more than 30 days")
    private int leaveBalance;



}
