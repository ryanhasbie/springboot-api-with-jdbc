package com.springboot.templates.controller;

import com.springboot.templates.model.Student;
import com.springboot.templates.model.Subject;
import com.springboot.templates.model.request.SubjectRequest;
import com.springboot.templates.model.response.SuccessResponse;
import com.springboot.templates.service.ISubjectService;
import com.springboot.templates.util.SubjectKey;
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
@RequestMapping("/subjects")
@Validated
public class SubjectController {

    @Autowired
    ISubjectService iSubjectService;
    @Autowired
    ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity getAllSubject() {
        List<Subject> subjects =iSubjectService.list();
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Get all successfully", subjects));
    }

    @PostMapping
    public ResponseEntity createSubject(@Valid @RequestBody SubjectRequest subjectRequest) {
        Subject newSubject = modelMapper.map(subjectRequest, Subject.class);
        Subject result = iSubjectService.create(newSubject);
        return  ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>("Created successfully!", result));
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable String id) {
        Subject subject = iSubjectService.get(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Get data by id successfully", subject));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateSubject(@Valid @RequestBody SubjectRequest subjectRequest, @PathVariable String id) {
        Subject existingSubject = modelMapper.map(subjectRequest, Subject.class);
        iSubjectService.update(existingSubject,id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Updated successfully!", subjectRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteSubject(@PathVariable String id) {
        iSubjectService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Deleted successfully!", id));
    }

    @PostMapping("/bulk")
    public ResponseEntity createBulk(@Valid @RequestBody List<SubjectRequest> subjectsRequest) {
        List<Subject> subjects = subjectsRequest.stream().map(subjectRequest -> modelMapper.map(subjectRequest, Subject.class)).collect(Collectors.toList());
        Optional<List<Subject>> insertBulk = iSubjectService.createBulk(subjects);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Inserted successfully!", insertBulk));
    }

    @GetMapping(params = {"key", "value"})
    public ResponseEntity getBy(@RequestParam String key, @RequestParam String value) throws Exception {
        List<Subject> subjects = iSubjectService.findBy(SubjectKey.valueOf(key), value);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success get course by!", subjects));
    }

}
