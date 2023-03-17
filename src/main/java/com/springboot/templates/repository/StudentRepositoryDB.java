package com.springboot.templates.repository;

import com.springboot.templates.model.Student;
import com.springboot.templates.model.mapper.StudentMapper;
import com.springboot.templates.util.IRandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
public class StudentRepositoryDB implements IStudentRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private IRandomStringGenerator iRandomStringGenerator;

    private final String SQL_GET_ALL = "select * from students";
    private final String SQL_INSERT_STUDENT = "insert into students values(?,?,?,?)";
    private final String FIND_BY_ID = "select * from students where student_id=?";
    private final String SQL_UPDATE_STUDENT = "update students set first_name=?, last_name=?, email=? where student_id=?";
    private final String SQL_DELETE_STUDENT = "delete from students where student_id=?";

    public StudentRepositoryDB(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public Student create(Student student) throws Exception {
        try {
            student.setStudentId(iRandomStringGenerator.random());
            int result = jdbcTemplate.update(SQL_INSERT_STUDENT, student.getStudentId(), student.getFirstName(), student.getLastName(), student.getEmail());
            if (result <= 0) {
                throw new Exception("Failed to insert!");
            }
            return student;
        } catch (DataAccessException e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<Student> getAll() throws Exception {
        try {
            return jdbcTemplate.query(SQL_GET_ALL, new StudentMapper());
        } catch (DataAccessException e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Optional<Student> findById(String id) throws Exception {
        try {
            Student student = jdbcTemplate.queryForObject(FIND_BY_ID, new StudentMapper(), new Object[]{id});
            return Optional.ofNullable(student);
        } catch (DataAccessException e) {
            throw new Exception("Failed to get!");
        }
    }

    @Override
    public void update(Student student, String id) throws Exception {
        try {
            jdbcTemplate.update(SQL_UPDATE_STUDENT, student.getFirstName(), student.getLastName(), student.getEmail(), id);
        } catch (DataAccessException e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void delete(String id) throws Exception {
        try {
            jdbcTemplate.update(SQL_DELETE_STUDENT, id);
        } catch (DataAccessException e) {
            throw new Exception("Failed to delete!");
        }
    }

    @Override
    public Optional<List<Student>> createBulk(List<Student> students) throws Exception {
        try {
            jdbcTemplate.batchUpdate(SQL_INSERT_STUDENT, students, 100, (PreparedStatement ps, Student student) -> {
                student.setStudentId(iRandomStringGenerator.random());
               ps.setString(1, student.getStudentId());
               ps.setString(2, student.getFirstName());
               ps.setString(3, student.getLastName());
               ps.setString(4, student.getEmail());
            });
            return Optional.of(students);
        } catch (DataAccessException e) {
            throw new Exception("Failed create bulk!");
        }
    }
}
