package com.example.hospital.models;

import jakarta.persistence.*;

import com.example.hospital.models.enums.*;


@Entity
@Table(name = "doctors")
public class Doctor extends User {
    @Enumerated(EnumType.STRING)
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
        Speciality speciality
    ) {
        super(firstName, lastName, email, password, phone, age);
        this.setRole(Role.DOCTOR);
        this.speciality = speciality;
    }

    public Speciality getSpeciality() {
        return this.speciality;
    }
}
