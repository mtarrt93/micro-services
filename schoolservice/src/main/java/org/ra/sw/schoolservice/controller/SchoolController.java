package org.ra.sw.schoolservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ra.sw.schoolservice.dto.SchoolSaveRequest;
import org.ra.sw.schoolservice.dto.SchoolStudentsResponse;
import org.ra.sw.schoolservice.entity.SchoolEntity;
import org.ra.sw.schoolservice.service.SchoolService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(path = "/api/schools")
public class SchoolController {
    private final ObjectMapper objectMapper;
    private final SchoolService schoolService;

    @GetMapping
    public ResponseEntity<List<SchoolEntity>> getAll(final HttpServletRequest httpReq) {
        log.info(">>> {} {}", httpReq.getMethod(), httpReq.getServletPath());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.schoolService.getAll());
    }

    @GetMapping(path = "{school-id}/with-students")
    public ResponseEntity<SchoolStudentsResponse> getAllWithStudents(final HttpServletRequest httpReq, @PathVariable(value = "school-id") Long schoolId) {
        log.info(">>> {} {}", httpReq.getMethod(), httpReq.getServletPath());
        Optional<SchoolStudentsResponse> schoolStudentsResponse = this.schoolService.getSchoolAndRelativeStudentsBySchoolId(schoolId);
        return null;
    }

    @PostMapping
    public ResponseEntity<SchoolEntity> save(final HttpServletRequest httpReq, @RequestBody SchoolSaveRequest schoolToSave) throws ServletException, IOException {
        log.info(">>> {} {}", httpReq.getMethod(), httpReq.getServletPath());
        log.info(">>> body={}", objectMapper.writeValueAsString(schoolToSave));
        final SchoolEntity school = SchoolEntity.builder()
            .name(schoolToSave.name())
            .email(schoolToSave.email())
            .build();
        Optional<SchoolEntity> schoolSaved = this.schoolService.save(school);
        return schoolSaved
                .map(studentEntity -> ResponseEntity.status(HttpStatus.CREATED)
                .body(studentEntity))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }
}
