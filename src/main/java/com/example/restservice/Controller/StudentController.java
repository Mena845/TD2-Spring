package com.example.restservice.Controller;

import com.example.restservice.BadRequestException;
import com.example.restservice.StudentValidator;
import com.example.restservice.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    private final StudentService service = new StudentService();
    private final StudentValidator validator = new StudentValidator();

    @PostMapping("/students")
    public ResponseEntity<?> createStudents(@RequestBody List<Student> newStudents) {

        try {
            // Validation
            validator.validate(newStudents);

            // Service
            List<Student> result = service.addStudents(newStudents);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(result);

        } catch (BadRequestException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur serveur");
        }
    }
}