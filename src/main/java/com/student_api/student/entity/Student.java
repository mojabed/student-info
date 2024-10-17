package com.student_api.student.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "students")
public record Student(String id, String firstName, String lastName, String major, String email) {}
