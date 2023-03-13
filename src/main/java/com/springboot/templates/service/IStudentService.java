package com.springboot.templates.service;

import com.springboot.templates.model.Student;

import java.util.List;
import java.util.Optional;

public interface IStudentService {
    Student create(Student student);
    List<Student> list();
    Student get(String id);
    void update(Student student, String id);
    void delete(String id);
    Optional<List<Student>> createBulk(List<Student> students);
}
