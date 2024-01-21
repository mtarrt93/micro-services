package org.ra.sw.schoolservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ra.sw.schoolservice.entity.StudentEntity;
import org.ra.sw.schoolservice.repository.StudentRepository;
import org.ra.sw.schoolservice.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    @Override
    public List<StudentEntity> getAll() {
        log.info("getAll - START");
        List<StudentEntity> students = this.studentRepository.findAll();
        log.info("Fetch {} students", students.size());
        return  students;
    }

    @Override
    public List<StudentEntity> getAllBySchoolId(Long schoolId) {
        log.info("getAllBySchoolId - START - param={}", schoolId);
        List<StudentEntity> students = this.studentRepository.findBySchoolId(schoolId);
        log.info("Fetch {} students", students.size());
        return  students;
    }

    @Override
    public Optional<StudentEntity> save(StudentEntity student) {
        log.info("save - START - param={} ", student);
        Objects.requireNonNull(student, "student to saving must not be null...");
        if( Objects.isNull(student.getId()) ) {
            student = this.studentRepository.save(student);
            log.info("saved student with id:{}", student.getId());
            return Optional.of(student);
        }
        return Optional.empty();
    }
}