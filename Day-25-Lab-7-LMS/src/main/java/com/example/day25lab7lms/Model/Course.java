package com.example.day25lab7lms.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Course {

    @NotEmpty(message = "id cannot be empty")
    @Size (min = 5,message = "id cannot be less then 5")
    private String courseID;

    @NotEmpty(message = "name cannot be empty")
    @Size(min = 5,max = 75, message = "name must be between 5 and 75 characters")
    private String courseName;

    @NotEmpty (message = "course MUST have a classroom")
    @Pattern(regexp = "^[A-B][0-9]{2}$",message = "class room should start with an uppercase (A) or (B)" +
            " followed by two digits (e.g, A01 B25 ..")
    private String classRoom;

    @NotEmpty (message = "topic must be filled")
    private String topic;
//    private Instructor instructor;

    @Pattern(regexp = "^(junior|intermediate|senior)$",message = "category must be junior - intermediate - senior")
    private String ageGroup;

    @PastOrPresent(message = "date must be in the past or present ")
    @JsonFormat (pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @FutureOrPresent(message = "date must be in the future or present")
    @JsonFormat (pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    private boolean isFinished;

}
