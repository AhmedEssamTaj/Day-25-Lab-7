package com.example.day25lab7lms.Controller;

import com.example.day25lab7lms.ApiResponse.ApiResponse;
import com.example.day25lab7lms.Model.Instructor;
import com.example.day25lab7lms.Service.InstructorService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/instructor")

public class InstructorController {

    private final InstructorService instructorService;

    @GetMapping("/get-all")
    public ResponseEntity getInstructors() {
        ArrayList<Instructor> instructors = instructorService.getInstructors();
        return ResponseEntity.status(200).body(instructors);
    }

    @PostMapping("/add")
    public ResponseEntity addInstructor(@RequestBody @Valid Instructor instructor, Errors errors) {

        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        instructorService.addInstructor(instructor);
        return ResponseEntity.status(200).body(new ApiResponse("instructor added successfully"));
    }

    @PutMapping("/update/{instructorId}")
    public ResponseEntity updateInstructor(@PathVariable String instructorId, @RequestBody @Valid Instructor instructor, Errors errors) {

        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        if (instructorService.updateInstructor(instructorId, instructor)) {
            return ResponseEntity.status(200).body(new ApiResponse("instructor updated successfully"));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse("no instructor with this id was found"));
        }
    }

    @DeleteMapping("/delete/{instructorId}")
    public ResponseEntity deleteInstructor(@PathVariable String instructorId) {
        if (instructorService.deleteInstructor(instructorId)) {
            return ResponseEntity.status(200).body(new ApiResponse("instructor deleted successfully"));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse("no instructor with this id was found"));
        }
    }

    // endpoint to get all the available instructors
    @GetMapping("/get-available")
    public ResponseEntity getAvailableInstructors() {
        ArrayList<Instructor> instructors = instructorService.getAvailableInstructors();
        if (instructors == null) {
            return ResponseEntity.status(400).body(new ApiResponse("No instructor is Available"));
        } else {
            return ResponseEntity.status(200).body(instructors);
        }
    }

    // endpoint to get the available instructors specialized in a certain field of study
    @GetMapping("/get-fos/{fieldOfStudy}")
    public ResponseEntity getAvailableFOSInstructor(@PathVariable String fieldOfStudy) {
        ArrayList<Instructor> instructors = instructorService.getAvailableFOSInstructor(fieldOfStudy);
        if (instructors == null) {
            return ResponseEntity.status(400).body(new ApiResponse("No Available instructor specialized in this field of study "));
        }
        return ResponseEntity.status(200).body(instructors);
    }

    // endpoint to get the instructor from his office
    @GetMapping("/get-by-office/{office}")
    public ResponseEntity getInstructorByOffice(@PathVariable String office) {
        Instructor instructor = instructorService.getInstructorByOffice(office);
        if (instructor == null) {
            return ResponseEntity.status(400).body(new ApiResponse("No instructor found with this office"));
        }
        return ResponseEntity.status(200).body(instructor);
    }


    @PutMapping("update-vacation/{instructorId}")
    public ResponseEntity takeVacation(@PathVariable String instructorId) {
        int result = instructorService.takeVacation(instructorId);
        switch (result) {
            case 1:
                return ResponseEntity.status(200).body(new ApiResponse("Vacation taken successfully"));
            case -1:
                return ResponseEntity.status(400).body(new ApiResponse("No instructor with this id is found"));

            case -2:
                return ResponseEntity.status(400).body(new ApiResponse("instructor is already on vacation"));
            case -3:
                return ResponseEntity.status(400).body(new ApiResponse("instructor used all of his leave balance"));
            default:
                return ResponseEntity.status(400).body(new ApiResponse("no"));


        }
    }


}
