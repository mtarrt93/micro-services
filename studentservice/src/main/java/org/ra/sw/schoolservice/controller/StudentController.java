package org.ra.sw.schoolservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ra.sw.schoolservice.dto.StudentSaveRequest;
import org.ra.sw.schoolservice.entity.StudentEntity;
import org.ra.sw.schoolservice.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(path = "/api/students")
public class StudentController {
    private final ObjectMapper objectMapper;
    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<List<StudentEntity>> getAll(final HttpServletRequest httpReq) {
        log.info(">>> {} {}", httpReq.getMethod(), httpReq.getServletPath());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.studentService.getAll());
    }

    @GetMapping(path = "/school-id/{school-id}")
    public ResponseEntity<List<StudentEntity>> getAllBySchoolId(final HttpServletRequest httpReq, @PathVariable(value = "school-id") Long schoolId) {
        log.info(">>> {} {}", httpReq.getMethod(), httpReq.getServletPath());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.studentService.getAllBySchoolId(schoolId));
    }

    @PostMapping
    public ResponseEntity<StudentEntity> save(final HttpServletRequest httpReq, @RequestBody StudentSaveRequest studentToSave) throws ServletException, IOException {
        log.info(">>> {} {}", httpReq.getMethod(), httpReq.getServletPath());
        log.info(">>> body={}", objectMapper.writeValueAsString(studentToSave));
        final StudentEntity student = StudentEntity.builder()
            .firstname(studentToSave.firstname())
            .lastname(studentToSave.lastname())
            .email(studentToSave.email())
            .schoolId(studentToSave.schoolId())
            .build();
        Optional<StudentEntity> studentSaved = this.studentService.save(student);
        return studentSaved
                .map(studentEntity -> ResponseEntity.status(HttpStatus.CREATED)
                .body(studentEntity))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }
}
