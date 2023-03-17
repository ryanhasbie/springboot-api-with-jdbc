package com.springboot.templates.service;

import com.springboot.templates.exception.NotFoundException;
import com.springboot.templates.model.Student;
import com.springboot.templates.repository.IStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService implements IStudentService {

    @Autowired
    IStudentRepository iStudentRepository;

    @Value("25")
    private Integer dataLength;

    @Override
    public Student create(Student student) {
        try {
            if (!(iStudentRepository.getAll().size() < dataLength)) {
                throw new Exception("Data cannot be more than 25");
            }
            List<Student> students = iStudentRepository.getAll();
            for (Student existingStudent: students) {
                if (existingStudent.getEmail().equalsIgnoreCase(student.getEmail())) {
                    throw new Exception("Email already exist!");
                }
            }
            return iStudentRepository.create(student);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Student> list() {
        try {
            List<Student> students = iStudentRepository.getAll();
            if (students.isEmpty()) {
                throw new NotFoundException();
            }
            return students;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Student get(String id) {
        try {
            Optional<Student> student = iStudentRepository.findById(id);
            if (student.isEmpty()) {
                throw new NotFoundException();
            }
            return student.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Student student, String id) {
        try {
//            Student existingCourse = get(id);
            Optional<Student> findBy = iStudentRepository.findById(id);
            if (findBy.isEmpty()) {
                throw new NotFoundException();
            }
            iStudentRepository.update(student, id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String id) {
        try {
            Optional<Student> findById = iStudentRepository.findById(id);
            if (findById.isEmpty()) {
                throw new NotFoundException();
            }
            iStudentRepository.delete(id);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public Optional<List<Student>> createBulk(List<Student> students) {
        try {
            if (!(iStudentRepository.getAll().size() + students.size() <= dataLength)) {
                throw new Exception("Data cannot be more than 25");
            }
            return iStudentRepository.createBulk(students);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }
}
