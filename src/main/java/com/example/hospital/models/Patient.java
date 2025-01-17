package com.example.hospital.models;

import jakarta.persistence.*;
import com.example.hospital.models.enums.Gender;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "patients")
public class Patient extends User {

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SurgeryTreatment> surgeryTreatments = new ArrayList<>();

    public Patient() {
        super();
    }

    public Patient(
        String firstName,
        String lastName,
        String email,
        String password,
        String phone,
        int age,
        Gender gender
    ) {
        super(firstName, lastName, email, password, phone, age, gender);
    }

    public List<SurgeryTreatment> getSurgeryTreatments() {
        return surgeryTreatments;
    }

    public void setSurgeryTreatments(List<SurgeryTreatment> surgeryTreatments) {
        this.surgeryTreatments = surgeryTreatments;
    }
}
