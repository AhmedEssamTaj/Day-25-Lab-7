package com.example.day25lab7lms.Service;

import com.example.day25lab7lms.Model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class StudentService {

    ArrayList <Student> students = new ArrayList<>();

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public boolean updateStudent(String studentId,Student student) {

        for (Student s : students) {
            if (s.getStudentId().equals(studentId)) {
                students.set(students.indexOf(s), student);
                return true;
            }
        }
        return false;
    }

    public boolean deleteStudent(String studentId) {
        for (Student s : students) {
            if (s.getStudentId().equals(studentId)) {
                students.remove(s);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Student> getAllStudentInCourse(String course) {
      ArrayList<Student> studentsInCourse = new ArrayList<>();
       for (Student s : students) {
            if (s.getCourse().equals(course)) {
                studentsInCourse.add(s);
            }
        }
       if (studentsInCourse.isEmpty() ){
           return null;
       }
       return studentsInCourse;
    }

    public boolean enrollStudent(String studentId, String course) {
        for (Student s : students) {
            if (s.getStudentId().equals(studentId)) {
                students.get(students.indexOf(s)).setCourse(course);
                students.get(students.indexOf(s)).setEnrolled(true);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Student> getAllFreeStudents() {
        ArrayList<Student> freeStudents = new ArrayList<>();
        for (Student s : students) {
            if (!s.isEnrolled()) {
                freeStudents.add(s);
            }
        }
        if (freeStudents.isEmpty()) {
            return null;
        }
        return freeStudents;

    }

    public ArrayList<Student> getAllJuniorStudents() {
        ArrayList<Student> juniorStudents = new ArrayList<>();
        for (Student s : students) {
            if (s.getAge() <18 ){
                juniorStudents.add(s);
            }
        }
        if (juniorStudents.isEmpty()) {
            return null;
        }
        return juniorStudents;
    }

}
