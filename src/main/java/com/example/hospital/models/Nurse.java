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
}
