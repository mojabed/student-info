package com.student_api.student.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Schema(description = "Student DTO Model Information")
public record StudentDto(
    String id,
    @Schema(description = "Student First Name")
        @Size(min = 2, max = 50, message = "First name must be at least two characters long")
        @NotEmpty(message = "First name is required")
        String firstName,
    @Schema(description = "Student Last Name")
        @Size(min = 2, max = 50, message = "Last name must be at least two characters long")
        @NotEmpty(message = "Last name is required")
        String lastName,
    @Schema(description = "Student Major of Studies") String major,
    @Schema(description = "Student Email")
        @Email(message = "Please provide a valid email address")
        @NotEmpty(message = "Email address is required")
        String email) {}
