package com.springboot.templates.controller;

import com.springboot.templates.model.Student;
import com.springboot.templates.model.Subject;
import com.springboot.templates.model.response.SuccessResponse;
import com.springboot.templates.service.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

    @Autowired
    ISubjectService iSubjectService;

    @GetMapping
    public ResponseEntity getAllSubject() {
        List<Subject> subjects =iSubjectService.list();
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Get all successfully", subjects));
    }

    @PostMapping
    public ResponseEntity createSubject(@RequestBody Subject subject) {
        Subject newSubject = iSubjectService.create(subject);
        return  ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>("Created successfully!", newSubject));
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable String id) {
        Subject subject = iSubjectService.get(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Get data by id successfully", subject));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateSubject(@RequestBody Subject subject, @PathVariable String id) {
        iSubjectService.update(subject,id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Updated successfully!", subject));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteSubject(@PathVariable String id) {
        iSubjectService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Deleted successfully!", id));
    }

    @PostMapping("/bulk")
    public ResponseEntity createBulk(@RequestBody List<Subject> subjects) {
        Optional<List<Subject>> subjects1 = iSubjectService.createBulk(subjects);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Inserted successfully!", subjects1));
    }
}
