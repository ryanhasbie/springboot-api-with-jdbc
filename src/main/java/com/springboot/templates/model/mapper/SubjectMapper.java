package com.springboot.templates.model.mapper;

import com.springboot.templates.model.Subject;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SubjectMapper implements RowMapper<Subject> {
    @Override
    public Subject mapRow(ResultSet rs, int rowNum) throws SQLException {
        Subject subject = new Subject();
        subject.setSubjectId(rs.getString("subject_id"));
        subject.setSubjectName(rs.getString("subject_name"));
        return subject;
    }
}
