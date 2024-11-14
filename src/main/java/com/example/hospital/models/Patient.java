package com.example.hospital.models;

import jakarta.persistence.*;
import com.example.hospital.models.enums.Gender;

import java.util.List;

@Entity
@Table(name = "patients")
public class Patient extends User {

    // One patient can have multiple treatments
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<PatientTreatment> treatments;

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

    // Getters and Setters for treatments (if needed)
    public List<PatientTreatment> getTreatments() {
        return treatments;
    }

    public void setTreatments(List<PatientTreatment> treatments) {
        this.treatments = treatments;
    }
}
