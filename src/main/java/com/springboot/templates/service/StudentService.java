package com.springboot.templates.service;

import com.springboot.templates.model.Student;
import com.springboot.templates.repository.IStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService implements IStudentService {

    @Autowired
    IStudentRepository iStudentRepository;

    @Override
    public Student create(Student student) {
        try {
            return iStudentRepository.create(student);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Student> list() {
        try {
            return iStudentRepository.getAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Student> get(String id) {
        try {
            return iStudentRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
