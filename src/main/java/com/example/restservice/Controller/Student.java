package com.example.restservice.Controller;

public class Student {
    private String reference;
    private String firstName;
    private String lastName;
    private int age;

    // Getters et Setters
    public String getReference() { return reference; }
    public void setReference(String reference) { this.reference = reference; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
}