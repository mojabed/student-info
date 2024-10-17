package com.student_api.student.service;

import com.student_api.student.dto.StudentDto;
import com.student_api.student.exception.DuplicateEmailException;
import java.util.List;

// TODO dto vs entity

public interface StudentService { // public by default?
  StudentDto createStudent(StudentDto studentDto) throws DuplicateEmailException;

  StudentDto getStudentById(String id);

  List<StudentDto> getAllStudents();

  StudentDto updateStudent(String id, StudentDto studentDto);

  void deleteStudent(String id);
}
