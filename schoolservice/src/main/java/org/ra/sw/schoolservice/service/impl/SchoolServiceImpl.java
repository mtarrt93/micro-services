package org.ra.sw.schoolservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ra.sw.schoolservice.dto.SchoolStudentsResponse;
import org.ra.sw.schoolservice.entity.SchoolEntity;
import org.ra.sw.schoolservice.repository.SchoolRepository;
import org.ra.sw.schoolservice.service.SchoolService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchoolServiceImpl implements SchoolService {
    private final SchoolRepository studentRepository;

    @Override
    public List<SchoolEntity> getAll() {
        log.info("getAll - START");
        List<SchoolEntity> students = this.studentRepository.findAll();
        log.info("Fetch {} students", students.size());
        return  students;
    }

    @Override
    public Optional<SchoolEntity> getById(Long id) {
        log.info("getById - START - param={}", id);
        return this.studentRepository.findById(id);
    }

    @Override
    public Optional<SchoolStudentsResponse> getSchoolAndRelativeStudentsBySchoolId(Long schoolId) {
        log.info("getSchoolAndRelativeStudentsBySchoolId - START - param={}", schoolId);
        Optional<SchoolEntity> school = this.getById(schoolId);
        if( school.isEmpty() ) {
            return Optional.empty();
        }
        // call service located on students that retrieved students by school id

        return Optional.empty();
    }

    @Override
    public Optional<SchoolEntity> save(SchoolEntity school) {
        log.info("save - START - param={} ", school);
        Objects.requireNonNull(school, "school to saving must not be null...");
        if( Objects.isNull(school.getId()) ) {
            school = this.studentRepository.save(school);
            log.info("saved school with id:{}", school.getId());
            return Optional.of(school);
        }
        return Optional.empty();
    }
}