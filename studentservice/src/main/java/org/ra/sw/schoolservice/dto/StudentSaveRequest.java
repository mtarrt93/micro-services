package org.ra.sw.schoolservice.dto;

public record StudentSaveRequest(String firstname, String lastname, String email, Long schoolId) { }