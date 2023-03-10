package com.springboot.templates.repository;

import com.springboot.templates.model.Subject;

import java.util.List;
import java.util.Optional;

public interface ISubjectRepository {
    Subject create(Subject subject);
    List<Subject> getAll() throws Exception;
    Optional<Subject> findById(String id) throws Exception;
}
