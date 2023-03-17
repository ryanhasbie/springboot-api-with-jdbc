package com.springboot.templates.controller;

import com.springboot.templates.model.Student;
import com.springboot.templates.model.Subject;
import com.springboot.templates.model.Teacher;
import com.springboot.templates.model.request.TeacherRequest;
import com.springboot.templates.model.response.SuccessResponse;
import com.springboot.templates.service.ITeacherService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/teachers")
@Validated
public class TeacherController {

    @Autowired
    ITeacherService iTeacherService;
    @Autowired
    ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity getAllTeacher() {
        List<Teacher> teachers = iTeacherService.list();
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Get all successfully!", teachers));
    }

    @PostMapping
    public ResponseEntity createTeacher(@Valid @RequestBody TeacherRequest teacherRequest) {
        Teacher newTeacher = modelMapper.map(teacherRequest, Teacher.class);
        Teacher result = iTeacherService.create(newTeacher);
        return  ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>("Created successfully", result));
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable String id) {
        Teacher teacher = iTeacherService.get(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Get data by id successfully", teacher));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateTeacher(@Valid @RequestBody TeacherRequest teacherRequest, @PathVariable String id) {
        Teacher existingTeacher = modelMapper.map(teacherRequest, Teacher.class);
        iTeacherService.update(existingTeacher, id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Updated successfully", teacherRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTeacher(@PathVariable String id) {
        iTeacherService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Deleted successfully", id));
    }

    @PostMapping("/bulk")
    public ResponseEntity createBulk(@Valid @RequestBody List<TeacherRequest> teachersRequest) {
        List<Teacher> teachers = teachersRequest.stream().map(teacherRequest -> modelMapper.map(teacherRequest, Teacher.class)).collect(Collectors.toList());
        Optional<List<Teacher>> insertBulk = iTeacherService.createBulk(teachers);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Inserted successfully!", insertBulk));
    }
}
