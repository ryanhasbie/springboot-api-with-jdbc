package com.springboot.templates.repository;

import com.springboot.templates.model.Student;
import com.springboot.templates.model.Subject;
import com.springboot.templates.util.SubjectKey;

import java.util.List;
import java.util.Optional;

public interface ISubjectRepository {
    Subject create(Subject subject) throws Exception;
    List<Subject> getAll() throws Exception;
    Optional<Subject> findById(String id) throws Exception;
    void update(Subject Subject, String id) throws Exception;
    void delete(String id) throws Exception;
    Optional<List<Subject>> createBulk(List<Subject> subjects) throws Exception;
    Optional<List<Subject>> findBy(SubjectKey by, String value) throws Exception;
}
