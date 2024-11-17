package com.example.day21lab5.Controller;

import com.example.day21lab5.ApiResponse.ApiResponse;
import com.example.day21lab5.Model.Student;
import jdk.jfr.Unsigned;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    ArrayList<Student> students = new ArrayList();

    // Endpoint to get all students obj
    @GetMapping("/get/all")
    public ArrayList<Student> getStudents() {
        return students;
    }

    // Endpoint to add students
    @PostMapping("/add")
    public ApiResponse addStudent(@RequestBody Student student) {
        if (student.getGPA() > 5 || student.getGPA() < 0) {
            return new ApiResponse("GPA MUST BE between 0 and 5");
        }
        students.add(student);
        return new ApiResponse("Student added successfully");
    }

    // Endpoint to update a student
    @PutMapping("/update/{id}")
    public ApiResponse updateStudent(@PathVariable String id, @RequestBody Student student) {

        for (Student s : students) {
            if (s.getId().equals(id)) {

                students.set(students.indexOf(s), student);
                return new ApiResponse("Student updated successfully");

            }

        }
        return new ApiResponse("Student not found");
    }

    // Endpoint to delete a student
    @DeleteMapping("/delete/{id}")
    public ApiResponse deleteStudent(@PathVariable String id) {
        for (Student s : students) {
            if (s.getId().equals(id)) {
                students.remove(students.indexOf(s));
                return new ApiResponse("Student deleted successfully");
            }
        }
        return new ApiResponse("Student not found");
    }

    // endpoint to classfiy students into honorary categories
    @GetMapping("/get/honorary/{honorary}")
    public ArrayList<Student> honoraryCategories(@PathVariable int honorary) {
        ArrayList<Student> firstHonor = new ArrayList();
        ArrayList<Student> secondtHonor = new ArrayList();
        ArrayList<Student> thirdHonor = new ArrayList();
        for (Student s : students) {
            if (s.getGPA() > 4.5) {
                firstHonor.add(s);
            } else if (s.getGPA() > 4.0) {
                secondtHonor.add(s);
            } else if (s.getGPA() > 3.5) {
                thirdHonor.add(s);
            }
        }
        if (honorary == 1) {
            return firstHonor;
        } else if (honorary == 2) {
            return secondtHonor;
        } else if (honorary == 3) {
            return thirdHonor;
        } else {
            return new ArrayList();
        }

    }

    // Endpoint to display the students with gpa greater then the average of gpa
@GetMapping("/get/higher/average")
    public ArrayList<Student> getHigherThenAverage() {
        ArrayList<Student> higherAverage = new ArrayList();
        // calculate the average GPA for students
        double average = 0;
        for (Student s : students) {
            average += s.getGPA();
            }
        average /= students.size();
        // add them to the arraylist
        for (Student s : students) {
            if (s.getGPA() > average) {
                higherAverage.add(s);
            }
        }
        return higherAverage;

    }

}
