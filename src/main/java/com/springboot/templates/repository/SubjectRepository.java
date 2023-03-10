package com.springboot.templates.repository;

import com.springboot.templates.model.Student;
import com.springboot.templates.model.Subject;
import com.springboot.templates.util.IRandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class SubjectRepository implements ISubjectRepository {

    @Autowired
    IRandomStringGenerator iRandomStringGenerator;

    private List<Subject> subjects = new ArrayList<>();

    @Override
    public Subject create(Subject subject) {
        subject.setSubjectId(iRandomStringGenerator.random());
        subjects.add(subject);
        return subject;
    }

    @Override
    public List<Subject> getAll() throws Exception {
        return subjects;
    }

    @Override
    public Optional<Subject> findById(String id) throws Exception {
        return Optional.empty();
    }
}
