package com.springboot.templates.repository;

import com.springboot.templates.model.Student;
import com.springboot.templates.util.IRandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class StudentRepository implements IStudentRepository {

    @Autowired
    IRandomStringGenerator iRandomStringGenerator;

    private List<Student> students = new ArrayList<>();

    @Override
    public Student create(Student student) {
        student.setStudentId(iRandomStringGenerator.random());
        students.add(student);
        return student;
    }

    @Override
    public List<Student> getAll() throws Exception {
        return students;
    }

    @Override
    public Optional<Student> findById(String id) throws Exception {
        for (Student student: students) {
            if (student.getStudentId().equals(id)) {
                return Optional.of(student);
            }
        }
        return Optional.empty();
    }
}
