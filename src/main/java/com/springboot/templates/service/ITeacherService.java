package com.springboot.templates.service;

import com.springboot.templates.model.Teacher;

import java.util.List;
import java.util.Optional;

public interface ITeacherService {
    Teacher create(Teacher teacher);
    List<Teacher> list();
    Optional<Teacher> get(String id);
}
