package com.example.hospital.models;

import jakarta.persistence.*;
import com.example.hospital.models.enums.Gender;

@Entity
@Table(name = "patients")

public class Patient extends User {


    // Default constructor
    public Patient() {
        super();
    }

    // Parameterized constructor
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
    }


}
