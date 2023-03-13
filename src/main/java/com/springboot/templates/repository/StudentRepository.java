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
    public Student create(Student student) throws Exception {
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

    @Override
    public void update(Student student, String id) throws Exception {
        for (Student existingStudent: students) {
            if (existingStudent.getStudentId().equals(id)) {
                existingStudent.setFirstName(student.getFirstName());
                existingStudent.setLastName(student.getLastName());
                existingStudent.setEmail(student.getEmail());
                break;
            }
        }
    }

    @Override
    public void delete(String id) throws Exception {
        for (Student student : students) {
            if (student.getStudentId().equals(id)) {
                students.remove(student);
                break;
            }
        }
    }

    @Override
    public Optional<List<Student>> createBulk(List<Student> students) throws Exception {
//        for (Student student: students) {
//            this.students.add(student);
//        }
        for (Student student : students) {
            student.setStudentId(iRandomStringGenerator.random());
        }
        this.students.addAll(students);
        return Optional.of(students);

    }
}
