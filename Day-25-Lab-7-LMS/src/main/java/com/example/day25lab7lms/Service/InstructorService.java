package com.example.day25lab7lms.Service;

import com.example.day25lab7lms.Model.Instructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class InstructorService {

    ArrayList <Instructor> instructors = new ArrayList<>();


    public ArrayList <Instructor> getInstructors(){
        return instructors;
    }

    public void addInstructor(Instructor instructor){
        instructors.add(instructor);
    }

    public boolean deleteInstructor(String instructorId){
        for (Instructor i : instructors) {
            if (i.getInstructorId().equals(instructorId)) {
                instructors.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean updateInstructor(String instructorId, Instructor instructor){
        for (Instructor i : instructors) {
            if (i.getInstructorId().equals(instructorId)) {
                instructors.set(instructors.indexOf(i), instructor);
                return true;
            }
        }
        return false;
    }

    // method to get all the available instructors ..
    public ArrayList<Instructor> getAvailableInstructors(){
        ArrayList<Instructor> availableInstructors = new ArrayList<>();
        for (Instructor i : instructors) {
            if (!i.isOnVacation()){
                availableInstructors.add(i);
            }
        }
        if (availableInstructors.isEmpty()){
            return null;
        }
        return availableInstructors;
    }

    // method to get the available instructors on a specific field of study
    public ArrayList<Instructor> getAvailableFOSInstructor (String fieldOfStudy){
        ArrayList<Instructor> availableFOSInstructors = new ArrayList<>();
        for (Instructor i : instructors) {
            if (( ! i.isOnVacation() ) && i.getFieldOfStudy().equalsIgnoreCase(fieldOfStudy)){ // instructor is not on vacation and is
                // specialized in this FOS
                availableFOSInstructors.add(i);
            }
        }
        if (availableFOSInstructors.isEmpty()){
            return null;
        }
        return availableFOSInstructors;
    }

    // method to get instructor by office
    public Instructor getInstructorByOffice(String instructorOffice){
        for (Instructor i : instructors) {
            if (i.getInstructorOffice().equals(instructorOffice)){
                return i;
            }
        }
        return null;
    }

    // method to give instructor vacation
    public int takeVacation (String instructorId){
        for (Instructor i : instructors) {
            if (i.getInstructorId().equals(instructorId)){
                if(i.isOnVacation()){
                    return -2;
                }else {
                    if (i.getLeaveBalance()<1){
                        return -3;
                    }else {
                        instructors.get(instructors.indexOf(i)).setOnVacation(true);
                        instructors.get(instructors.indexOf(i)).setLeaveBalance(i.getLeaveBalance()-1);
                        return 1;
                    }
                }
            }
        }

        return -1;

    }



}
