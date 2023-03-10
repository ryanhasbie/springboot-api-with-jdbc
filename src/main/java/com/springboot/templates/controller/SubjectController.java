package com.springboot.templates.controller;

import com.springboot.templates.model.Student;
import com.springboot.templates.model.Subject;
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
        if (subjects.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Data masih kosong!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(subjects);
    }

    @PostMapping
    public ResponseEntity createSubject(@RequestBody Subject subject) {
        Subject newSubject = iSubjectService.create(subject);
        return  ResponseEntity.status(HttpStatus.CREATED).body(newSubject);
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable String id) {
        Optional<Subject> subject = iSubjectService.get(id);
        if (subject.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Data tidak ditemukan!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(subject);
    }
}
