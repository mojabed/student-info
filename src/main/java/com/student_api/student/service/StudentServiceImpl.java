package com.student_api.student.service;

import com.student_api.student.dto.StudentDto;
import com.student_api.student.entity.Student;
import com.student_api.student.exception.DuplicateEmailException;
import com.student_api.student.exception.ResourceNotFoundException;
import com.student_api.student.mapper.StudentMapper;
import com.student_api.student.repository.StudentRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

  private final StudentRepository studentRepository;

  public StudentServiceImpl(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  @Override
  public StudentDto createStudent(StudentDto studentDto) throws DuplicateEmailException {
    if (studentRepository.existsByEmail(studentDto.email())) {
      throw new DuplicateEmailException(studentDto.email());
    }
    studentDto.id();

    Student student = StudentMapper.MAPPER.mapToStudent(studentDto);
    Student savedStudent = studentRepository.save(student);
    StudentDto savedStudentDto = StudentMapper.MAPPER.mapToStudentDto(savedStudent);

    return savedStudentDto;
  }

  @Override
  public List<StudentDto> getAllStudents() {
    List<Student> students = studentRepository.findAll();
    return students.stream()
        .map(StudentMapper.MAPPER::mapToStudentDto)
        .collect(Collectors.toList());
  }

  @Override
  public StudentDto getStudentById(String id) {
    Student student =
        studentRepository
            .findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Student", "id", id));
    return StudentMapper.MAPPER.mapToStudentDto(student);
  }

  @Override
  public StudentDto updateStudent(String id, StudentDto studentDto) {
    if (!id.equals(studentDto.id())) {
      throw new IllegalArgumentException(
          "ID in the URL does not match the ID in the request body.");
    }
    Student existingStudent =
        studentRepository
            .findById(studentDto.id())
            .orElseThrow(() -> new ResourceNotFoundException("Student", "id", studentDto.id()));

    Student updatedStudent =
        new Student(
            existingStudent.id(),
            studentDto.firstName(),
            studentDto.lastName(),
            studentDto.major(),
            studentDto.email());

    updatedStudent = studentRepository.save(updatedStudent);
    return StudentMapper.MAPPER.mapToStudentDto(updatedStudent);
  }

  @Override
  public void deleteStudent(String id) {
    Student existingStudent = studentRepository.findById(id).orElseThrow();
    studentRepository.deleteById(id);
  }
}
