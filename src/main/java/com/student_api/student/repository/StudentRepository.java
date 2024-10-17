package com.student_api.student.repository;

import com.student_api.student.entity.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

// TODO read about mongo repositories

@Repository
public interface StudentRepository extends MongoRepository<Student, String> {
  boolean existsByEmail(String email);
}
