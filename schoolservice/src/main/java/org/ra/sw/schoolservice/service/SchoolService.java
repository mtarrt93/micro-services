package org.ra.sw.schoolservice.service;

import org.ra.sw.schoolservice.dto.SchoolStudentsResponse;
import org.ra.sw.schoolservice.entity.SchoolEntity;

import java.util.List;
import java.util.Optional;

public interface SchoolService {
    List<SchoolEntity> getAll();

    Optional<SchoolEntity> getById(Long id);

    Optional<SchoolStudentsResponse> getSchoolAndRelativeStudentsBySchoolId(Long id);

    Optional<SchoolEntity> save(SchoolEntity school);
}
