package com.example.restservice.Controller;

import com.example.restservice.Exception.BadRequestException;
import com.example.restservice.validator.StudentValidator;
import com.example.restservice.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    private final StudentService service = new StudentService();
    private final StudentValidator validator = new StudentValidator();

    //  A) GET /welcome
    @GetMapping("/welcome")
    public ResponseEntity<?> welcome(@RequestParam(required = false) String name) {

        if (name == null || name.isBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Paramètre name manquant");
        }

        return ResponseEntity.ok("Welcome " + name);
    }

    //  B) POST /students
    @PostMapping("/students")
    public ResponseEntity<?> createStudents(@RequestBody List<Student> newStudents) {

        try {
            validator.validate(newStudents);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(service.addStudents(newStudents));

        } catch (BadRequestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur serveur");
        }
    }

    //  C) GET /students
    @GetMapping("/students")
    public ResponseEntity<?> getStudents(@RequestHeader(value = "Accept", required = false) String accept) {

        try {
            if (accept == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Header Accept manquant");
            }

            if (!accept.equals("text/plain") && !accept.equals("application/json")) {
                return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                        .body("Format non supporté");
            }

            if (accept.equals("text/plain")) {
                String result = service.getStudents().stream()
                        .map(s -> s.getFirstName() + " " + s.getLastName())
                        .reduce((a, b) -> a + ", " + b)
                        .orElse("");

                return ResponseEntity.ok(result);
            }

            return ResponseEntity.ok(service.getStudents());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur serveur");
        }
    }
}