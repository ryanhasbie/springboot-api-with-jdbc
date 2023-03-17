package com.springboot.templates.repository;

import com.springboot.templates.model.Student;
import com.springboot.templates.model.Teacher;
import com.springboot.templates.model.mapper.StudentMapper;
import com.springboot.templates.model.mapper.TeacherMapper;
import com.springboot.templates.util.IRandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
@Primary
public class TeacherRepositoryDB implements ITeacherRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private IRandomStringGenerator iRandomStringGenerator;

    private final String SQL_GET_ALL = "select * from teachers";
    private final String SQL_INSERT = "insert into teachers values(?,?,?,?)";
    private final String FIND_BY_ID = "select * from teachers where teacher_id=?";
    private final String SQL_UPDATE = "update teacher set first_name=?, last_name=?, email=? where student_id=?";
    private final String SQL_DELETE = "delete from teacher where teacher_id=?";

    public TeacherRepositoryDB(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public Teacher create(Teacher teacher) throws Exception {
        try {
            teacher.setTeacherId(iRandomStringGenerator.random());
            int result = jdbcTemplate.update(SQL_INSERT, teacher.getTeacherId(), teacher.getFirstName(), teacher.getLastName(), teacher.getEmail());
            if (result <= 0) {
                throw new Exception("Failed to insert!");
            }
            return teacher;
        } catch (DataAccessException e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<Teacher> getAll() throws Exception {
        try {
            return jdbcTemplate.query(SQL_GET_ALL, new TeacherMapper());
        } catch (DataAccessException e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Optional<Teacher> findById(String id) throws Exception {
        try {
            Teacher teacher = jdbcTemplate.queryForObject(FIND_BY_ID, new TeacherMapper(), new Object[]{id});
            return Optional.ofNullable(teacher);
        } catch (DataAccessException e) {
            throw new Exception("Failed to get!");
        }
    }

    @Override
    public void update(Teacher teacher, String id) throws Exception {
        try {
            jdbcTemplate.update(SQL_UPDATE, teacher.getFirstName(), teacher.getLastName(), teacher.getEmail(), id);
        } catch (DataAccessException e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void delete(String id) throws Exception {
        try {
            jdbcTemplate.update(SQL_DELETE, id);
        } catch (DataAccessException e) {
            throw new Exception("Failed to delete!");
        }
    }

    @Override
    public Optional<List<Teacher>> createBulk(List<Teacher> teachers) throws Exception {
        try {
            jdbcTemplate.batchUpdate(SQL_INSERT, teachers, 100, (PreparedStatement ps, Teacher teacher) -> {
                teacher.setTeacherId(iRandomStringGenerator.random());
                ps.setString(1, teacher.getTeacherId());
                ps.setString(2, teacher.getFirstName());
                ps.setString(3, teacher.getLastName());
                ps.setString(4, teacher.getEmail());
            });
            return Optional.of(teachers);
        } catch (DataAccessException e) {
            throw new Exception("Failed create bulk!");
        }
    }
}
