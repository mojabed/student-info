package com.student_api.student.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatusCode;

public class ErrorDetails {

  private final LocalDateTime timestamp;
  private final HttpStatusCode statusCode;
  private final String message;
  private final String path;
  private final String errorCode;

  public ErrorDetails(
      LocalDateTime timestamp,
      HttpStatusCode statusCode,
      String message,
      String path,
      String errorCode) {
    this.timestamp = timestamp;
    this.statusCode = statusCode;
    this.message = message;
    this.path = path;
    this.errorCode = errorCode;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public String getMessage() {
    return message;
  }

  public String getPath() {
    return path;
  }

  public String getErrorCode() {
    return errorCode;
  }

  @JsonProperty("statusCode")
  public int getStatusCodeValue() {
    return statusCode.value();
  }
}
