package com.example.day25lab7lms.Service;

import com.example.day25lab7lms.Model.Course;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;

@Service
public class CourseService {

    ArrayList<Course> courses = new ArrayList<>();

    // method to get all courses
    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public boolean deleteCourse(String courseId) {
        for (Course c : courses) {
            if (c.getCourseID().equals(courseId)) {
                courses.remove(c);
                return true;
            }
        }
        return false;
    }

    public boolean updateCourse(String id, Course course) {
        for (Course c : courses) {
            if (c.getCourseID().equals(id)) {
                courses.set(courses.indexOf(c), course);
                return true;
            }
        }
        return false;
    }

    // method to get all courses by age group
    public ArrayList <Course> getCourseByAgeGroup(String ageGroup) {
        ArrayList<Course> ageGroupCourses = new ArrayList<>();
        for (Course c : courses) {
            if (c.getAgeGroup().equals(ageGroup)) {
                ageGroupCourses.add(c);
            }
        }
        if (ageGroupCourses.isEmpty()){
            return null;
        }else {
            return ageGroupCourses;
        }
    }

    // method to state course finish
    public boolean finishCourse (String courseId) {
        for (Course c : courses) {
            if (c.getCourseID().equals(courseId)) {
                if (c.isFinished()){// if course is already finished
                    return false;
                }else {
                    courses.get(courses.indexOf(c)).setFinished(true);
                    return true;
                }
            }
        }
        return false;
    }

    public ArrayList <Course> getOngoingCourses() {
        ArrayList<Course> ongoingCourses = new ArrayList<>();
        for (Course c : courses) {
            if (!c.isFinished()){
                ongoingCourses.add(c);
            }
        }
        if (ongoingCourses.isEmpty()){
            return null;
        }
        return ongoingCourses;
    }

    // calculate the reamining days
    public int getCourseReminingDays (String courseId) {
       int reminingDays = -1;
        for (Course c : courses) {
            if (c.getCourseID().equals(courseId)) {

                Date date = new Date();
                String modifiedDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
                reminingDays = (int) ChronoUnit.DAYS.between(LocalDate.parse(modifiedDate),c.getEndDate());

            }
        }
        return reminingDays;
    }

}
