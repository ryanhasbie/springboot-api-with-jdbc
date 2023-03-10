package com.springboot.templates.service;

import com.springboot.templates.model.Subject;

import java.util.List;
import java.util.Optional;

public interface ISubjectService {
    Subject create(Subject subject);
    List<Subject> list();
    Optional<Subject> get(String id);
}
