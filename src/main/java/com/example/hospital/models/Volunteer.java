package com.example.hospital.models;

import com.example.hospital.models.enums.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "volunteers")
public class Volunteer extends User {
    
    @NotNull(message = "Skills are required")
    @Column(nullable = false)
    private String skills;

    @NotNull(message = "Availability is required")
    @Column(nullable = false)
    private String availability;

    public Volunteer() {}

    public Volunteer(
        String firstName,
        String lastName,
        String email,
        String password,
        String phone,
        int age,
        Gender gender,
        String skills,
        String availability
    ) {
        super(firstName, lastName, email, password, phone, age, gender);
        this.skills = skills;
        this.availability = availability;
    }

    // Getters and Setters
    public String getSkills() { return skills; }
    public void setSkills(String skills) { this.skills = skills; }

    public String getAvailability() { return availability; }
    public void setAvailability(String availability) { this.availability = availability; }
}
