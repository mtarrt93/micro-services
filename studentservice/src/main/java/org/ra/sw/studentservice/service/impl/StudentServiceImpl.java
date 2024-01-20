package org.ra.sw.studentservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.SecondaryRow;
import org.ra.sw.studentservice.entity.StudentEntity;
import org.ra.sw.studentservice.repository.StudentRepository;
import org.ra.sw.studentservice.service.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    @Override
    public void save(StudentEntity student) {
        Objects.requireNonNull(student, "student to saving must not be null...");
        if( Objects.isNull(student.getId()) ) {
            this.studentRepository.save(student);
            log.info("saved student with id:{}", student.getId());
        }
    }
}