package com.springboot.templates.controller;

import com.springboot.templates.model.Student;
import com.springboot.templates.model.Subject;
import com.springboot.templates.model.Teacher;
import com.springboot.templates.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    @Autowired
    ITeacherService iTeacherService;

    @GetMapping
    public ResponseEntity getAllTeacher() {
        List<Teacher> teachers = iTeacherService.list();
        if (teachers.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Data masih kosong!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(teachers);
    }

    @PostMapping
    public ResponseEntity createStudent(@RequestBody Teacher teacher) {
        Teacher newTeacher = iTeacherService.create(teacher);
        return  ResponseEntity.status(HttpStatus.CREATED).body(newTeacher);
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable String id) {
        Optional<Teacher> teacher = iTeacherService.get(id);
        if (teacher.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Data tidak ditemukan!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(teacher);
    }
}
