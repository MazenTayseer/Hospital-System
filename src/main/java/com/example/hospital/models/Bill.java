package com.example.hospital.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private LocalDate billDate;

    public Bill() {}

    public Bill(Patient patient, double amount, LocalDate billDate) {
        this.patient = patient;
        this.amount = amount;
        this.billDate = billDate;
    }

    // Getters and Setters
}
