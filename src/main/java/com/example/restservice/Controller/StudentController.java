package com.example.restservice.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class StudentController {
    private static List<Student> students = new ArrayList<>();

    // a) GET /welcome?name
    @GetMapping("/welcome")
    public ResponseEntity<String> welcome(@RequestParam(required = false) String name) {

        if (name == null || name.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST) // 400
                    .body("Paramètre 'name' manquant");
        }

        return ResponseEntity
                .status(HttpStatus.OK) // 200
                .body("Welcome " + name);
    }
    // B) POST /students
    @PostMapping("/students")
    public List<Student> createStudents(@RequestBody List<Student> newStudents) {
        students.addAll(newStudents);

       return students;
    }

    // C) GET /students avec header Accept
    @GetMapping("/students")
    public String getStudents(@RequestHeader(name = "Accept") String header) {

        if (header != null && !header.equals("text/plain") && !header.equals("application/json")) {
            return students.stream()
                    .map(s -> s.getFirstName() + " " + s.getLastName())
                    .collect(Collectors.joining(", "));
        } else {
            return "Format non supporté";
        }
    }
}