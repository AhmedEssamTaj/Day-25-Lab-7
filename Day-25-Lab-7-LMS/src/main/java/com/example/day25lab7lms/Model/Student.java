package com.example.day25lab7lms.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Student {

    @NotEmpty(message = "id cannot be empty")
    @Size(min = 5,message = "id cannot be less then 5")
    private String studentId;

    @NotEmpty(message = "name cannot be empty")
    @Size(min = 3,max = 20, message = "name must be between 3 and 20 characters")
    private String studentName;

    @NotNull(message = "age cannot be empty")
    @Min(value = 12,message = "studen age must be at least 12 years old")
    @Max(value = 120, message = "student age cannot be more than 120!")
    private int age;

    private boolean isEnrolled;

    @Size(min = 5,max = 75, message = "course name must be between 5 and 75 characters")
    private String course;

    @NotEmpty (message = "gender cannot be null")
    @Pattern(regexp = "^(male|female)$", message = "gender can only be male or female")
    private String gender;



}
