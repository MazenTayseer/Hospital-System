package com.example.hospital.models;

import jakarta.persistence.*;

import com.example.hospital.enums.*;


@Entity
@Table(name = "doctors")
public class Doctor extends User {
    @Enumerated(EnumType.STRING)
    public Speciality speciality;

    public Doctor(String firstName, String lastName, String email, String password, int age, Gender gender,
            Speciality speciality) {
        super(firstName, lastName, email, password, password, age, gender);
        this.speciality = speciality;
        this.setRole(Role.DOCTOR);
    }
}
