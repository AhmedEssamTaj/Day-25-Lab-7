package com.example.day25lab7lms.Controller;

import com.example.day25lab7lms.ApiResponse.ApiResponse;
import com.example.day25lab7lms.Model.Course;
import com.example.day25lab7lms.Service.CourseService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/course")
public class CourseController {

   private final CourseService courseService;

    @GetMapping("/get-all")
    public ResponseEntity getCourses() {
        return ResponseEntity.status(200).body(courseService.getCourses());
    }

    @PostMapping("/add")
    public ResponseEntity addCourse(@RequestBody @Valid Course course, Errors errors) {

        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        courseService.addCourse(course);
        return ResponseEntity.status(200).body(new ApiResponse("course added Successfully"));

    }

    @PutMapping("/update/{courseId}")
    public ResponseEntity updateCourse(@PathVariable String courseId,@RequestBody @Valid Course course, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        if (courseService.updateCourse(courseId, course)) {
            return ResponseEntity.status(200).body(new ApiResponse("course updated successfully"));
        }else {
            return ResponseEntity.status(400).body(new ApiResponse("no course with this id is found"));
        }
    }

    @DeleteMapping("/delete/{courseId}")
    public ResponseEntity deleteCourse(@PathVariable String courseId) {
        if (courseService.deleteCourse(courseId)) {
            return ResponseEntity.status(200).body(new ApiResponse("course deleted successfully"));
        }else {
            return ResponseEntity.status(400).body(new ApiResponse("no course with this id is found"));
        }
    }

    // endpoint to get courses by group age
    @GetMapping("/get/age-group/{ageGroup}")
    public ResponseEntity getCoursesByAgeGroup (@PathVariable String ageGroup){

        ArrayList <Course> ageGroupCourses = courseService.getCourseByAgeGroup(ageGroup);
        if (ageGroupCourses == null){
            return ResponseEntity.status(400).body(new ApiResponse("no courses with this age group found"));
        }
        return ResponseEntity.status(200).body(ageGroupCourses);

    }

    @PutMapping("/update/finish/{courseId}")
    public ResponseEntity finishCourse (@PathVariable String courseId) {
        if (courseService.finishCourse(courseId)) {
            return ResponseEntity.status(200).body(new ApiResponse("course finish successfully"));
        }else {
            return ResponseEntity.status(400).body(new ApiResponse("no course with this id found"));
        }
    }

    @GetMapping("/get/ongoing")
    public ResponseEntity getOngoingCourses (){
        ArrayList<Course> ongoingCourses = courseService.getOngoingCourses();
        if (ongoingCourses == null){
            return ResponseEntity.status(400).body(new ApiResponse("no ongoing courses found"));
        }
        return ResponseEntity.status(200).body(ongoingCourses);
    }

    @GetMapping("/get/remain-days/{courseId}")
    public ResponseEntity getCourseReminingDays (@PathVariable String courseId){
        int remainingDays = courseService.getCourseReminingDays(courseId);
        if (remainingDays == -1){
            return ResponseEntity.status(400).body(new ApiResponse("no course with this id found"));
        }else {
            return ResponseEntity.status(200).body(remainingDays);
        }

    }



}
