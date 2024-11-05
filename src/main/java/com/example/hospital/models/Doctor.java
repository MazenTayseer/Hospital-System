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

    public Speciality getSpeciality() {
        return this.speciality;
    }
}
