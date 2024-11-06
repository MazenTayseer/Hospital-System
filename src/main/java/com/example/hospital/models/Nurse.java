package com.example.hospital.models;

import com.example.hospital.models.enums.*;

import jakarta.persistence.*;

@Entity
@Table(name = "nurses")
public class Nurse extends User {
    public Nurse() {
        super();
        this.setRole(Role.NURSE);
    }

    public Nurse(
        String firstName,
        String lastName,
        String email,
        String password,
        String phone,
        int age
    ) {
        super(firstName, lastName, email, password, phone, age);
        this.setRole(Role.NURSE);
    }
}
