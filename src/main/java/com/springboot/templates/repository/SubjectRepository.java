package com.springboot.templates.repository;

import com.springboot.templates.model.Student;
import com.springboot.templates.model.Subject;
import com.springboot.templates.util.IRandomStringGenerator;
import com.springboot.templates.util.SubjectKey;
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
    public Subject create(Subject subject) throws Exception {
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

    @Override
    public void update(Subject Subject, String id) throws Exception {
        for (Subject existingSubject: subjects) {
            if (existingSubject.getSubjectId().equals(id)) {
                existingSubject.setSubjectName(Subject.getSubjectName());
                break;
            }
        }
    }

    @Override
    public void delete(String id) throws Exception {
        for (Subject subject: subjects) {
            if (subject.getSubjectId().equals(id)) {
                subjects.remove(subject);
                break;
            }
        }
    }

    @Override
    public Optional<List<Subject>> createBulk(List<Subject> subjects) throws Exception {
        for (Subject subject : subjects) {
            subject.setSubjectId(iRandomStringGenerator.random());
        }
        this.subjects.addAll(subjects);
        return Optional.of(subjects);
    }

    @Override
    public Optional<List<Subject>> findBy(SubjectKey by, String value) throws Exception {
        List<Subject> results = new ArrayList<>();
        for (Subject subject: subjects) {
            switch (by) {
                case subjectName :
                    if (subject.getSubjectName().toLowerCase().equalsIgnoreCase(value)) {
                        results.add(subject);
                    }
            }
        }
        return results.isEmpty() ? Optional.empty() : Optional.of(results);
    }
}
