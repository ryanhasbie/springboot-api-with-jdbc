package com.springboot.templates.repository;

import com.springboot.templates.model.Student;
import com.springboot.templates.model.Teacher;
import com.springboot.templates.util.IRandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TeacherRepository implements ITeacherRepository {

    @Autowired
    IRandomStringGenerator iRandomStringGenerator;

    private List<Teacher> teachers = new ArrayList<>();

    @Override
    public Teacher create(Teacher teacher) throws Exception {
        teacher.setTeacherId(iRandomStringGenerator.random());
        teachers.add(teacher);
        return teacher;
    }

    @Override
    public List<Teacher> getAll() throws Exception {
        return teachers;
    }

    @Override
    public Optional<Teacher> findById(String id) throws Exception {
        for (Teacher teacher: teachers) {
            if (teacher.getTeacherId().equals(id)) {
                return Optional.of(teacher);
            }
        }
        return Optional.empty();
    }

    @Override
    public void update(Teacher teacher, String id) throws Exception {
        for (Teacher existingTeacher: teachers) {
            if (existingTeacher.getTeacherId().equals(id)) {
                existingTeacher.setFirstName(teacher.getFirstName());
                existingTeacher.setLastName(teacher.getLastName());
                existingTeacher.setEmail(teacher.getEmail());
                break;
            }
        }
    }

    @Override
    public void delete(String id) throws Exception {
        for (Teacher teacher : teachers) {
            if (teacher.getTeacherId().equals(id)) {
                teachers.remove(teacher);
                break;
            }
        }
    }

    @Override
    public Optional<List<Teacher>> createBulk(List<Teacher> teachers) throws Exception {
        for (Teacher teacher : teachers) {
            teacher.setTeacherId(iRandomStringGenerator.random());
        }
        this.teachers.addAll(teachers);
        return Optional.of(teachers);
    }
}
