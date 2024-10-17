package com.student_api.student.controller;

import com.student_api.student.dto.StudentDto;
import com.student_api.student.exception.DuplicateEmailException;
import com.student_api.student.service.StudentServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(
    name = "CRUD REST APIs for Student Resource: ",
    description =
        "CRUD REST APIs - Create Student, Update Student, Get Student, Get All Students, Delete Student")
@RestController
@RequestMapping("/api/students")
public class StudentController {

  private final StudentServiceImpl studentServiceImpl;

  public StudentController(StudentServiceImpl studentServiceImpl) {
    this.studentServiceImpl = studentServiceImpl;
  }

  @Operation(
      summary = "Create Student for REST API",
      description = "The Create Student REST API saves student data to a MongoDB database.")
  @ApiResponse(responseCode = "201", description = "Http Status 201 Created")
  @PostMapping
  public ResponseEntity<StudentDto> createStudent(@Valid @RequestBody StudentDto studentDto)
      throws DuplicateEmailException {
    StudentDto createdStudent = studentServiceImpl.createStudent(studentDto);
    return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
  }

  @Operation(
      summary = "Get Student by ID REST API",
      description =
          "The Get Student By ID REST API is used to retrieve a student from a MongoDB database.")
  @ApiResponse(responseCode = "200", description = "Http Status 200 Success")
  @GetMapping("/{id}")
  public ResponseEntity<StudentDto> getStudentById(@PathVariable String id) {
    Optional<StudentDto> student = Optional.ofNullable(studentServiceImpl.getStudentById(id));
    if (student.isPresent()) {
      return new ResponseEntity<>(student.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @Operation(
      summary = "Update Student REST API",
      description =
          "The Update Student REST API is used to update student data by ID in a MongoDB database.")
  @ApiResponse(responseCode = "200", description = "Http Status 200 Success")
  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteStudent(@PathVariable String id) {

    studentServiceImpl.deleteStudent(id);
    String message = String.format("Student with ID %s was deleted.", id);
    return new ResponseEntity<>(message, HttpStatus.OK);
  }

  @Operation(
          summary = "Update Student REST API",
          description =
                  "The Update Student REST API is used to update student data by ID in a MongoDB database.")
  @ApiResponse(responseCode = "200", description = "Http Status 200 Success")
  @PutMapping("/{id}")
  public ResponseEntity<StudentDto> updateStudent(
          @PathVariable String id, @RequestBody @Valid StudentDto studentDto) {
    studentDto.id();
    StudentDto updatedStudent = studentServiceImpl.updateStudent(id, studentDto);
    return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
  }
}
