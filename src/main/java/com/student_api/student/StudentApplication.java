package com.student_api.student;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


// Swagger UI http://localhost:8080/swagger-ui/index.html#/
@SpringBootApplication
@OpenAPIDefinition(
    info =
        @Info(
            title = "Spring Boot Student Rest API Documentation",
            description = "Spring Boot Rest (Student) API Documentation",
            version = "v1.0",
            contact =
                @Contact(
                    name = "Muhammad Abed",
                    email = "jken223@gmail.com",
                    url = "https://www.linkedin.com/in/abedmo"),
            license = @License(name = "Apache 2.0", url = "https://www.someUrl.com")),
    externalDocs =
        @ExternalDocumentation(
            description = "Mo's GitHub",
            url = "https://github.com/mojabed"))
public class StudentApplication {

  public static void main(String[] args) {
    SpringApplication.run(StudentApplication.class, args);
  }
}
