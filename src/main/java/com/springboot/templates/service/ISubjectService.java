package com.springboot.templates.service;

import com.springboot.templates.model.Student;
import com.springboot.templates.model.Subject;
import com.springboot.templates.util.SubjectKey;

import java.util.List;
import java.util.Optional;

public interface ISubjectService {
    Subject create(Subject subject);
    List<Subject> list();
    Subject get(String id);
    void update(Subject subject, String id);
    void delete(String id);
    Optional<List<Subject>> createBulk(List<Subject> subjects);
    List<Subject> findBy(SubjectKey by, String value) throws Exception;
}
