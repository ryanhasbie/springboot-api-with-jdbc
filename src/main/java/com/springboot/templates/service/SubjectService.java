package com.springboot.templates.service;

import com.springboot.templates.model.Subject;
import com.springboot.templates.repository.ISubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService implements ISubjectService {

    @Autowired
    ISubjectRepository iSubjectRepository;

    @Override
    public Subject create(Subject subject) {
        try {
            return iSubjectRepository.create(subject);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Subject> list() {
        try {
            return iSubjectRepository.getAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Subject> get(String id) {
        try {
            return iSubjectRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
