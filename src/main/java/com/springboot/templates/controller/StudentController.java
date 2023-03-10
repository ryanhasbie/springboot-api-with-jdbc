package com.springboot.templates.controller;


import com.springboot.templates.model.Student;
import com.springboot.templates.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    IStudentService iStudentService;

    @GetMapping
    public ResponseEntity getAllStudent() {
        List<Student> students =iStudentService.list();
        if (students.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Data masih kosong!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(students);
    }

    @PostMapping
    public ResponseEntity createStudent(@RequestBody Student student) {
        Student newStudent = iStudentService.create(student);
        return  ResponseEntity.status(HttpStatus.CREATED).body(newStudent);
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable String id) {
        Optional<Student> student = iStudentService.get(id);
        if (student.isEmpty()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Data tidak ditemukan!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(student);
    }

}
