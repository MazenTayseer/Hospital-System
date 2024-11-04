package com.example.hospital.models;

import com.example.hospital.enums.*;

import jakarta.persistence.*;


@Entity
@Table(name = "managers")
public class Manager extends User {
    public Manager(String firstName, String lastName, String email, String password, String phone, int age,
            Gender gender) {
        super(firstName, lastName, email, password, phone, age, gender);
        this.setRole(Role.MANAGER);
    }

}
