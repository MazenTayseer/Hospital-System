package com.example.hospital.models;

import jakarta.persistence.*;
import com.example.hospital.models.enums.Gender;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "patients")

public class Patient extends User {

    // One patient can have multiple treatments
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<PatientTreatment> treatments = new ArrayList<>();

    // Default constructor
    public Patient() {
        super();
    }

    // Parameterized constructor
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

    // Getters and Setters for treatments
    public List<PatientTreatment> getTreatments() {
        return treatments;
    }

    public void setTreatments(List<PatientTreatment> treatments) {
      this.treatments = treatments;
    }
     // Optional: Method to get the most recent treatment
     public PatientTreatment getMostRecentTreatment() {
       return treatments.isEmpty() ? null : treatments.get(treatments.size() - 1);
     }
     public void addTreatment(PatientTreatment treatment) {
      treatments.add(treatment);
  }
}
