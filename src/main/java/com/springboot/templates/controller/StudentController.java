package com.springboot.templates.controller;


import com.springboot.templates.model.Student;
import com.springboot.templates.model.response.SuccessResponse;
import com.springboot.templates.service.IStudentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    IStudentService iStudentService;

//    @Autowired
//    ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity getAllStudent() {
        List<Student> students =iStudentService.list();
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Get all successfully!", students));
    }

    @PostMapping
    public ResponseEntity createStudent(@RequestBody Student student) {
        Student newStudent = iStudentService.create(student);
        return  ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>("Created successfully!", newStudent));
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable String id) {
        Student student = iStudentService.get(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Get data by id successfully", student));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateStudent(@RequestBody Student student, @PathVariable String id) {
        iStudentService.update(student, id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success updated", student));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteStudent(@PathVariable String id) {
        iStudentService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Deleted successfully", id));
    }

    @PostMapping("/bulk")
    public ResponseEntity createBulk(@RequestBody List<Student> studentList) {
        Optional<List<Student>> students = iStudentService.createBulk(studentList);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Inserted successfully!", students));
    }

}
