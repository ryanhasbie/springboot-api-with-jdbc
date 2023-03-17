package com.springboot.templates.service;

import com.springboot.templates.exception.NotFoundException;
import com.springboot.templates.model.Subject;
import com.springboot.templates.repository.ISubjectRepository;
import com.springboot.templates.util.SubjectKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService implements ISubjectService {

    @Autowired
    ISubjectRepository iSubjectRepository;

    @Value("9")
    private Integer dataLength;

    @Override
    public Subject create(Subject subject) {
        try {
            if (!(iSubjectRepository.getAll().size() < dataLength)) {
                throw new Exception("Data cannot be more than 6");
            }
//            Optional<List<Subject>> subject1 = iSubjectRepository.findBy(SubjectKey.subjectName, subject.getSubjectName());
//            if (subject1.isPresent()) {
//                throw new Exception("Subject name already exist!");
//            }

            List<Subject> subjects = iSubjectRepository.getAll();
            for (Subject existingSubject: subjects) {
                if (existingSubject.getSubjectName().equalsIgnoreCase(subject.getSubjectName())) {
                    throw new Exception("Subject already exists!");
                }
            }
            return iSubjectRepository.create(subject);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Subject> list() {
        try {
            List<Subject> subjects = iSubjectRepository.getAll();
            if (subjects.isEmpty()) {
                throw new NotFoundException();
            }
            return subjects;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Subject get(String id) {
        try {
            Optional<Subject> subject = iSubjectRepository.findById(id);
            if (subject.isEmpty()) {
                throw new NotFoundException();
            }
            return subject.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Subject subject, String id) {
        try {
            Optional<List<Subject>> subject1 = iSubjectRepository.findBy(SubjectKey.subjectName, subject.getSubjectName());
            Optional<Subject> findById = iSubjectRepository.findById(id);
            if (subject1.isPresent()) {
                throw new Exception("Subject name already exist");
            }
            if (findById.isEmpty()) {
                throw new NotFoundException();
            }
            iSubjectRepository.update(subject, id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String id) {
        try {
            Optional<Subject> findById = iSubjectRepository.findById(id);
            if (findById.isEmpty()) {
                throw new NotFoundException();
            }
            iSubjectRepository.delete(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<List<Subject>> createBulk(List<Subject> subjects) {
        try {
            List<Subject> getAll = iSubjectRepository.getAll();
            if (subjects.stream().anyMatch(s -> getAll.stream().anyMatch(ga -> ga.getSubjectName().equalsIgnoreCase(s.getSubjectName())))) {
                throw new Exception("Subject name can not duplicate");
            }

            if (subjects.stream().map(Subject::getSubjectName).distinct().count() < subjects.size()) {
                throw new Exception("Name in one bulk can not be duplicate");
            }

            if (!(iSubjectRepository.getAll().size() + subjects.size() <= dataLength)) {
                    throw new Exception("Data cannot be more than 9");
            }
            return iSubjectRepository.createBulk(subjects);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Subject> findBy(SubjectKey by, String value) throws Exception {
        Optional<List<Subject>> subjects = iSubjectRepository.findBy(by, value);
        if (subjects.isEmpty()) {
            throw new Exception("Course not found!");
        }
        return subjects.get();
    }
}
