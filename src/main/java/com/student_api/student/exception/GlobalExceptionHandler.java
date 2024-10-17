package com.student_api.student.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorDetails> handleGlobalException(
      Exception exception, WebRequest webRequest) {
    HttpStatusCode statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
    ErrorDetails errorDetails =
        new ErrorDetails(
            LocalDateTime.now(),
            statusCode,
            exception.getMessage(),
            webRequest.getDescription(false),
            "INTERNAL SERVER ERROR");

    return new ResponseEntity<>(errorDetails, statusCode);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      HttpHeaders headers,
      HttpStatusCode status,
      WebRequest request) {

    Map<String, String> errors = new HashMap<>();
    List<ObjectError> errorList = ex.getBindingResult().getAllErrors();

    errorList.forEach(
        (error) -> {
          String fieldName = ((FieldError) error).getField();
          String message = error.getDefaultMessage();
          System.out.println(String.format("fieldName: %s | message: %s", fieldName, message));
          errors.put(fieldName, message);
        });
    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
  }
}
