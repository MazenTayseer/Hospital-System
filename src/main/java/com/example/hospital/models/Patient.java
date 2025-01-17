package com.example.hospital.models;

import jakarta.persistence.*;
import com.example.hospital.models.enums.Gender;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "patients")
public class Patient extends User {

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SurgeryTreatment> surgeryTreatments = new ArrayList<>();
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nurse_id")
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private Nurse assignedNurse;

    @Column(nullable = true)
    private String medicalHistory;

    public Patient() {
        super();
    }

    // Parameterized constructor
    // public Patient(
    // String firstName,
    // String lastName,
    // String email,
    // String password,
    // String phone,
    // int age,
    // Gender gender) {
    // super(firstName, lastName, email, password, phone, age, gender);
    // }

    public Patient(
            String firstName,
            String lastName,
            String email,
            String password,
            String phone,
            int age,
            Gender gender,
            Nurse assignedNurse,
            String medicalHistory) {
        super(firstName, lastName, email, password, phone, age, gender);
        this.assignedNurse = assignedNurse;
        this.medicalHistory = medicalHistory;
    }

    public List<SurgeryTreatment> getSurgeryTreatments() {
        return surgeryTreatments;
    }
    // Getters and Setters
    public Nurse getAssignedNurse() {
        return assignedNurse;
    }

    public void setAssignedNurse(Nurse assignedNurse) {
        this.assignedNurse = assignedNurse;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public void setSurgeryTreatments(List<SurgeryTreatment> surgeryTreatments) {
        this.surgeryTreatments = surgeryTreatments;
    }
}
