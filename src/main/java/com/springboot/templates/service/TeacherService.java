package com.springboot.templates.service;

import com.springboot.templates.exception.NotFoundException;
import com.springboot.templates.model.Teacher;
import com.springboot.templates.repository.ITeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService implements ITeacherService {

    @Autowired
    ITeacherRepository iTeacherRepository;
    @Value("6")
    private Integer dataLength;

    @Override
    public Teacher create(Teacher teacher) {
        try {
            if (!(iTeacherRepository.getAll().size() < dataLength)) {
                throw new Exception("Data is full!");
            }
            return iTeacherRepository.create(teacher);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Teacher> list() {
        try {
            List<Teacher> teachers = iTeacherRepository.getAll();
            if (teachers.isEmpty()) {
                throw new NotFoundException();
            }
            return teachers;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Teacher get(String id) {
        try {
            Optional<Teacher> teacher = iTeacherRepository.findById(id);
            if (teacher.isEmpty()) {
                throw new NotFoundException();
            }
            return teacher.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Teacher teacher, String id) {
        try {
            Teacher existingTeacher = get(id);
            iTeacherRepository.update(teacher, existingTeacher.getTeacherId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String id) {
        try {
            Optional<Teacher> findById = iTeacherRepository.findById(id);
            if (findById.isEmpty()) {
                throw new NotFoundException();
            }
            iTeacherRepository.delete(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<List<Teacher>> createBulk(List<Teacher> teachers) {
        try {
            if (!(iTeacherRepository.getAll().size() < dataLength)) {
                throw new Exception("Data is full!");
            }
            return iTeacherRepository.createBulk(teachers);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
