package com.springboot.templates.repository;

import com.springboot.templates.model.Teacher;

import java.util.List;
import java.util.Optional;

public interface ITeacherRepository {
    Teacher create(Teacher teacher);
    List<Teacher> getAll() throws Exception;
    Optional<Teacher> findById(String id) throws Exception;
}
