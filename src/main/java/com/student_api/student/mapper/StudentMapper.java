package com.student_api.student.mapper;

import com.student_api.student.dto.StudentDto;
import com.student_api.student.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

// TODO learn map struct

@Mapper
public interface StudentMapper {

  StudentMapper MAPPER = Mappers.getMapper(StudentMapper.class);

  StudentDto mapToStudentDto(Student student);

  Student mapToStudent(StudentDto studentDto);
}
