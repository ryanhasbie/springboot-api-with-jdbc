package com.springboot.templates.repository;

import com.springboot.templates.model.Student;
import com.springboot.templates.model.Teacher;

import java.util.List;
import java.util.Optional;

public interface ITeacherRepository {
    Teacher create(Teacher teacher) throws Exception;
    List<Teacher> getAll() throws Exception;
    Optional<Teacher> findById(String id) throws Exception;
    void update(Teacher teacher, String id) throws Exception;
    void delete(String id) throws Exception;
    Optional<List<Teacher>> createBulk(List<Teacher> teachers) throws Exception;
}
