package com.springboot.templates.service;

import com.springboot.templates.model.Teacher;
import com.springboot.templates.repository.ITeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService implements ITeacherService {

    @Autowired
    ITeacherRepository iTeacherRepository;

    @Override
    public Teacher create(Teacher teacher) {
        try {
            return iTeacherRepository.create(teacher);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Teacher> list() {
        try {
            return iTeacherRepository.getAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Teacher> get(String id) {
        try {
            return iTeacherRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
