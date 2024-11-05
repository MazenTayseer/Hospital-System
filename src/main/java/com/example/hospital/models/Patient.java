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
}
