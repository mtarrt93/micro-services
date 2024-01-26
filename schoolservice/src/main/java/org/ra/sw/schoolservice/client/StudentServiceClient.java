package org.ra.sw.schoolservice.client;

import org.ra.sw.schoolservice.dto.StudentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(
    name = "${application.config-external-service.student-service.name}",
    url =  "${application.config-external-service.student-service.base-url}"
)
public interface StudentServiceClient {
    @GetMapping(path = "/school-id/{school-id}")
    public ResponseEntity<List<StudentDto>> getAllStudentsBy(@PathVariable(name = "school-id") Long schoolId);
}