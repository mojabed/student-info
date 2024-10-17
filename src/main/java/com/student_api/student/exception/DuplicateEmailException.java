package com.student_api.student.exception;

public class DuplicateEmailException extends Exception {
  public DuplicateEmailException(String email) {
    super(String.format("A student with the email '%s' already exists.", email));
  }
}
