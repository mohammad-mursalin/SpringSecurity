package com.example.SpringSecurity.controller;

import com.example.SpringSecurity.model.Student;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    private List<Student> students = new ArrayList<>(List.of(
            new Student("mursalin", 100, 80),
            new Student("lamon", 101, 90)));

    @GetMapping("/student")
    public List<Student> getStudents() {

        return students;
    }

    @PostMapping("/student")
    public Student addStudent(@RequestBody Student student) {

        students.add(student);
        return student;
    }

    @GetMapping("/csrf-token")
    public CsrfToken getToken(HttpServletRequest request) {

        return (CsrfToken) request.getAttribute("_csrf");
    }
}
