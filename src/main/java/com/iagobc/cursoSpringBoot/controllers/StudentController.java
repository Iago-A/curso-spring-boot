package com.iagobc.cursoSpringBoot.controllers;

import com.iagobc.cursoSpringBoot.domain.Student;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping ("/students")
public class StudentController {

    // Simulate a database with the students
    private List<Student> students = new ArrayList<>(Arrays.asList(
            new Student(1, "Mario Flores", "marioflores@gmail.com", 23, "1º DAW"),
            new Student(2, "Bibiana Cao", "bibic@gmail.com", 41, "2º DAM"),
            new Student(3, "Iago Blanco", "iagoblanco@gmail.com", 29, "2º DAM"),
            new Student(4, "Marina Gorrochategui", "gorrochateguimarina@gmail.com", 19, "2º DAW")
    ));


    @GetMapping
    public List<Student> showStudents () {
        return students;
    }


    @GetMapping ("/{email}")
    public Student showSpecificStudent (@PathVariable String email) {
        for (Student student : students) {
            if (student.getEmail().equalsIgnoreCase(email)) {
                return student;
            }
        }

        // This isn't a good practice
        return null;
    }


    @PostMapping
    public List<Student> addStudent (@RequestBody Student student) {
        students.add(student);

        return students;
    }


    @PatchMapping
    public Student updateStudent (@RequestBody Student studentUpdate) {
        for (Student student : students) {
            if (student.getId() == studentUpdate.getId()) {

                if (studentUpdate.getName() != null) {
                    student.setName(studentUpdate.getName());
                }

                if (studentUpdate.getEmail() != null) {
                    student.setEmail(studentUpdate.getEmail());
                }

                if (studentUpdate.getAge() != 0) {
                    student.setAge(studentUpdate.getAge());
                }

                if (studentUpdate.getGrade() != null) {
                    student.setGrade(studentUpdate.getGrade());
                }

                return student;
            }
        }

        // This isn't a good practice
        return null;
    }


    @DeleteMapping ("/{id}")
    public List<Student> deleteStudent (@PathVariable int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                students.remove(student);
                break;
            }
        }

        return students;
    }
}
