package com.example.day25lab7lms.Controller;

import com.example.day25lab7lms.ApiResponse.ApiResponse;
import com.example.day25lab7lms.Model.Student;
import com.example.day25lab7lms.Service.StudentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/get-all")
    public ResponseEntity getStudents() {
        ArrayList<Student> students = studentService.getStudents();
        return ResponseEntity.status(200).body(students);
    }

    @PostMapping("/add")
    public ResponseEntity addStudent(@RequestBody @Valid Student student, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        studentService.addStudent(student);
        return ResponseEntity.status(200).body(new ApiResponse("Student was added successfully"));
    }

    @PutMapping("/update/{studentId}")
    public ResponseEntity updateStudent(@PathVariable String studentId,@RequestBody @Valid Student student, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        if(studentService.updateStudent(studentId,student)){
            return ResponseEntity.status(200).body(new ApiResponse("Student was updated successfully"));
        }else {
            return ResponseEntity.status(400).body(new ApiResponse("No student with this id was found"));
        }
    }

    @DeleteMapping("/delete/{studentId}")
    public ResponseEntity deleteStudent(@PathVariable String studentId) {
        if (studentService.deleteStudent(studentId)) {
            return ResponseEntity.status(200).body(new ApiResponse("Student was deleted successfully"));
        }else {
            return ResponseEntity.status(400).body(new ApiResponse("No student with this id was found"));
        }
    }

    @GetMapping("/get/student-course/{course}")
    public ResponseEntity getAllStudentInCourse (@PathVariable String course) {
        ArrayList<Student> studentsInCourse = studentService.getAllStudentInCourse(course);
        if (studentsInCourse == null) {
            return ResponseEntity.status(400).body(new ApiResponse("No student in this course was found"));
        }
        return ResponseEntity.status(200).body(studentsInCourse);
    }


    @PutMapping("/update/enroll/{studentId}/{course}")
    public ResponseEntity enrollStudent(@PathVariable String studentId, @PathVariable String course) {
        boolean isEnrolled = studentService.enrollStudent(studentId, course);
        if (isEnrolled) {
            return ResponseEntity.status(200).body(new ApiResponse("Student was enrolled successfully"));
        }
        else {
            return ResponseEntity.status(400).body(new ApiResponse("No student with this id was found"));
        }
    }

    @GetMapping("/get/free")
    public ResponseEntity getAllFreeStudents(){
        ArrayList<Student> students = studentService.getAllFreeStudents();
        if (students == null) {
            return ResponseEntity.status(400).body(new ApiResponse("No free student found"));
        }
        return ResponseEntity.status(200).body(students);
    }

    @GetMapping("/get/junior")
    public ResponseEntity getAllJuniorStudents (){
        ArrayList<Student> junioStudents = studentService.getAllJuniorStudents();
        if (junioStudents == null) {
            return ResponseEntity.status(400).body(new ApiResponse("No junior student found"));
        }
        return ResponseEntity.status(200).body(junioStudents);
    }


}
