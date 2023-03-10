package com.springboot.templates.service;

import com.springboot.templates.model.Student;

import java.util.List;
import java.util.Optional;

public interface IStudentService {
    Student create(Student student);
    List<Student> list();
    Optional<Student> get(String id);
}
