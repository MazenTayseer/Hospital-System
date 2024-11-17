package com.example.hospital.models;

import com.example.hospital.models.enums.Gender;

import jakarta.persistence.*;

@Entity
@Table(name = "donors")
public class Donor extends User {
    public Donor() {}

    public Donor(
        String firstName,
        String lastName,
        String email,
        String password,
        String phone,
        int age,
        Gender gender
    ) {
        super(firstName, lastName, email, password, phone, age, gender);
    }
}
