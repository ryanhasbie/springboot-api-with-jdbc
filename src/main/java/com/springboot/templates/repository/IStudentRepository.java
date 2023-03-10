package com.springboot.templates.repository;

import com.springboot.templates.model.Student;

import java.util.List;
import java.util.Optional;

public interface IStudentRepository {

    Student create(Student student);
    List<Student> getAll() throws Exception;
    Optional<Student> findById(String id) throws Exception;
}
