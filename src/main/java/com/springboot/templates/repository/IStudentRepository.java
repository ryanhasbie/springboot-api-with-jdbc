package com.springboot.templates.repository;

import com.springboot.templates.model.Student;

import java.util.List;
import java.util.Optional;

public interface IStudentRepository {

    Student create(Student student) throws Exception;
    List<Student> getAll() throws Exception;
    Optional<Student> findById(String id) throws Exception;
    void update(Student student, String id) throws Exception;
    void delete(String id) throws Exception;
    Optional<List<Student>> createBulk(List<Student> students) throws Exception;
}
