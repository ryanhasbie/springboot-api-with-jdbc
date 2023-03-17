package com.springboot.templates.repository;

import com.springboot.templates.model.Subject;
import com.springboot.templates.model.mapper.SubjectMapper;
import com.springboot.templates.util.IRandomStringGenerator;
import com.springboot.templates.util.SubjectKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Primary
public class SubjectRepositoryDB implements ISubjectRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    IRandomStringGenerator iRandomStringGenerator;

    private final String SQL_GET_ALL = "select * from subjects";
    private final String SQL_INSERT = "insert into subjects values(?,?)";
    private final String FIND_BY_ID = "select * from subjects where subject_id=?";
    private final String SQL_UPDATE = "update subjects set subject_name=? where subject_id=?";
    private final String SQL_DELETE = "delete from subjects where subject_id=?";
    private final String FIND_BY = "select * from subjects where LOWER(subject_name) like LOWER(?)";

    @Override
    public Subject create(Subject subject) throws Exception {
        try {
            subject.setSubjectId(iRandomStringGenerator.random());
            int result = jdbcTemplate.update(SQL_INSERT, subject.getSubjectId(), subject.getSubjectName());
            if (result <= 0) {
                throw new Exception("Failed to insert!");
            }
            return subject;
        } catch (DataAccessException e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<Subject> getAll() throws Exception {
        try {
            return jdbcTemplate.query(SQL_GET_ALL, new SubjectMapper());
        } catch (DataAccessException e) {
            System.out.println("----slaha");
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Optional<Subject> findById(String id) throws Exception {
        try {
            Subject subject = jdbcTemplate.queryForObject(FIND_BY_ID, new SubjectMapper(), new Object[]{id});
            return Optional.ofNullable(subject);
        } catch (DataAccessException e) {
            throw new Exception("Failed to get!");
        }
    }

    @Override
    public void update(Subject Subject, String id) throws Exception {
        try {
            jdbcTemplate.update(SQL_UPDATE, Subject.getSubjectName(), id);
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
    public Optional<List<Subject>> createBulk(List<Subject> subjects) throws Exception {
        try {
            jdbcTemplate.batchUpdate(SQL_INSERT, subjects, 100, (PreparedStatement ps, Subject subject) -> {
                subject.setSubjectId(iRandomStringGenerator.random());
                ps.setString(1, subject.getSubjectId());
                ps.setString(2, subject.getSubjectName());
            });
            return Optional.of(subjects);
        } catch (DataAccessException e) {
            throw new Exception("Failed create bulk!");
        }
    }

    @Override
    public Optional<List<Subject>> findBy(SubjectKey by, String value) throws Exception {
     try {
            List<Subject> subjects = new ArrayList<>();
                    switch (by) {
                        case subjectName -> {
                            subjects = jdbcTemplate.query(FIND_BY, new SubjectMapper(), "%" + value + "%");
                        }
                    }
                    return Optional.of(subjects);
        } catch (DataAccessException e) {
            throw new Exception("Failed to find By");
        }
    }
}
