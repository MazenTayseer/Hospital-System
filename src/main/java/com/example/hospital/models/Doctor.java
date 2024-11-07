package com.example.hospital.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import com.example.hospital.models.enums.*;


@Entity
@Table(name = "doctors")
public class Doctor extends User {
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull(message = "The speciality is required")
    private Speciality speciality;

    public Doctor() {
        super();
        this.setRole(Role.DOCTOR);
    }

    public Doctor(
        String firstName,
        String lastName,
        String email,
        String password,
        String phone,
        int age,
        Gender gender,
        Speciality speciality
    ) {
        super(firstName, lastName, email, password, phone, age, gender);
        this.setRole(Role.DOCTOR);
        this.speciality = speciality;
    }

    public Speciality getSpeciality() {
        return this.speciality;
    }
}
