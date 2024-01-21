package org.ra.sw.schoolservice.dto;

import java.util.List;

public record SchoolStudentsResponse(String name, String email, List<StudentDto> studentDto) { }