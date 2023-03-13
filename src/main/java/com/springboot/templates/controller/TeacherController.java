package com.springboot.templates.controller;

import com.springboot.templates.model.Student;
import com.springboot.templates.model.Subject;
import com.springboot.templates.model.Teacher;
import com.springboot.templates.model.response.SuccessResponse;
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
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Get all successfully!", teachers));
    }

    @PostMapping
    public ResponseEntity createStudent(@RequestBody Teacher teacher) {
        Teacher newTeacher = iTeacherService.create(teacher);
        return  ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>("Created successfully", newTeacher));
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable String id) {
        Teacher teacher = iTeacherService.get(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Get data by id successfully", teacher));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateTeacher(@RequestBody Teacher teacher, @PathVariable String id) {
        iTeacherService.update(teacher, id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Updated successfully", teacher));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTeacher(@PathVariable String id) {
        iTeacherService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Deleted successfully", id));
    }

    @PostMapping("/bulk")
    public ResponseEntity createBulk(@RequestBody List<Teacher> teachers) {
        Optional<List<Teacher>> teachers1 = iTeacherService.createBulk(teachers);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Inserted successfully!", teachers1));
    }
}
