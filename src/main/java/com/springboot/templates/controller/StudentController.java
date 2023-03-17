package com.springboot.templates.controller;


import com.springboot.templates.model.Student;
import com.springboot.templates.model.request.StudentRequest;
import com.springboot.templates.model.response.SuccessResponse;
import com.springboot.templates.service.IStudentService;
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
@RequestMapping("/students")
@Validated
public class StudentController {

    @Autowired
    IStudentService iStudentService;
    @Autowired
    ModelMapper modelMapper;

//    @Autowired
//    ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity getAllStudent() {
        List<Student> students =iStudentService.list();
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Get all successfully!", students));
    }

    @PostMapping
    public ResponseEntity createStudent(@Valid @RequestBody StudentRequest studentRequest) {
//        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Student newStudent = modelMapper.map(studentRequest, Student.class);
        Student result = iStudentService.create(newStudent);
        return  ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>("Created successfully!", result));
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable String id) {
        Student student = iStudentService.get(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Get data by id successfully", student));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateStudent(@Valid @RequestBody StudentRequest studentRequest, @PathVariable String id) {
        Student existingStudent = modelMapper.map(studentRequest, Student.class);
        iStudentService.update(existingStudent, id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success updated", studentRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteStudent(@PathVariable String id) {
        iStudentService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Deleted successfully", id));
    }

    @PostMapping("/bulk")
    public ResponseEntity createBulk(@Valid @RequestBody List<StudentRequest> studentRequests) {
        List<Student> students = studentRequests.stream().map(studentRequest -> modelMapper.map(studentRequest, Student.class)).collect(Collectors.toList());
        Optional<List<Student>> insertBulk = iStudentService.createBulk(students);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Inserted successfully!", insertBulk));
    }

}
