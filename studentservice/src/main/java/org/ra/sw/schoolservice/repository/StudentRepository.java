package org.ra.sw.schoolservice.repository;

import org.ra.sw.schoolservice.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
    List<StudentEntity> findBySchoolId(Long schoolId);
}