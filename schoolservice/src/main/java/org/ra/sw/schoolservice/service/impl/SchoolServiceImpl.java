package org.ra.sw.schoolservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ra.sw.schoolservice.client.StudentServiceClient;
import org.ra.sw.schoolservice.dto.SchoolStudentsResponse;
import org.ra.sw.schoolservice.dto.StudentDto;
import org.ra.sw.schoolservice.entity.SchoolEntity;
import org.ra.sw.schoolservice.repository.SchoolRepository;
import org.ra.sw.schoolservice.service.SchoolService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchoolServiceImpl implements SchoolService {
    private final SchoolRepository studentRepository;
    private final StudentServiceClient  studentServiceClient;
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
            log.info("school with id={} not found", schoolId);
            return Optional.empty();
        }
        // call service located on students that retrieved students by school id
        log.info("Call external service for fetching students by school-id={} ...", schoolId);
        ResponseEntity<List<StudentDto>> studentsServiceResponse = this.studentServiceClient.getAllStudentsBy(schoolId);
        if( !studentsServiceResponse.getStatusCode().is2xxSuccessful() ) {
            throw new RuntimeException( String.format("Error to fetch student by student-service for school-id=%s", schoolId ) );
        }
        return Optional.of(
            new SchoolStudentsResponse(
                school.get().getName(),
                school.get().getEmail(),
                    studentsServiceResponse.getBody()
            )
        );
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