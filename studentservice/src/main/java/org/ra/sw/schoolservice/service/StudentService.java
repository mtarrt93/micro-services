package org.ra.sw.schoolservice.service;

import org.ra.sw.schoolservice.entity.StudentEntity;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<StudentEntity> getAll();

    List<StudentEntity> getAllBySchoolId(Long schoolId);

    Optional<StudentEntity> save(StudentEntity student);
}
