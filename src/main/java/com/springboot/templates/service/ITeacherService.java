package com.springboot.templates.service;

import com.springboot.templates.model.Student;
import com.springboot.templates.model.Teacher;

import java.util.List;
import java.util.Optional;

public interface ITeacherService {
    Teacher create(Teacher teacher);
    List<Teacher> list();
    Teacher get(String id);
    void update(Teacher teacher, String id);
    void delete(String id);
    Optional<List<Teacher>> createBulk(List<Teacher> teachers);
}
