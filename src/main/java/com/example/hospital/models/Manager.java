package com.example.hospital.models;

import com.example.hospital.models.enums.*;

import jakarta.persistence.*;


@Entity
@Table(name = "managers")
public class Manager extends User {
    public Manager() {
        super();
        this.setRole(Role.MANAGER);
    }

    public Manager(
        String firstName,
        String lastName,
        String email,
        String password,
        String phone,
        int age
    ) {
        super(firstName, lastName, email, password, phone, age);
        this.setRole(Role.MANAGER);
    }
}
