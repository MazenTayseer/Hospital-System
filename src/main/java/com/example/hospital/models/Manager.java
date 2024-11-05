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
}
