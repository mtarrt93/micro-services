package org.ra.sw.studentservice.service;

import org.ra.sw.studentservice.entity.StudentEntity;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<StudentEntity> getAll();

    Optional<StudentEntity> save(StudentEntity student);
}
