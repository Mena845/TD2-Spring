package com.example.restservice;

import com.example.restservice.Controller.Student;

import java.util.List;

public class StudentValidator {

    public void validate(List<Student> students) {

        for (Student s : students) {

            if (s.getReference() == null || s.getReference().isBlank()) {
                throw new BadRequestException("Reference cannot be null or empty");
            }

            if (s.getFirstName() == null || s.getFirstName().isBlank()) {
                throw new BadRequestException("FirstName cannot be null or empty");
            }

            if (s.getLastName() == null || s.getLastName().isBlank()) {
                throw new BadRequestException("LastName cannot be null or empty");
            }
        }
    }
}
