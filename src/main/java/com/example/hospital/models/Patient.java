package com.example.hospital.models;

import com.example.hospital.models.enums.*;

import jakarta.persistence.*;

@Entity
@Table(name = "patients")
public class Patient extends User {
    public Patient() {
        super();
        this.setRole(Role.PATIENT);
    }

    public Patient(
        String firstName,
        String lastName,
        String email,
        String password,
        String phone,
        int age,
        Gender gender
    ) {
        super(firstName, lastName, email, password, phone, age, gender);
        this.setRole(Role.PATIENT);
    }
}
