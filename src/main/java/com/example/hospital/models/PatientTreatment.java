package com.example.hospital.models;

import jakarta.persistence.*;
import java.time.LocalDate;  // Import LocalDate for the treatment date

@Entity
public class PatientTreatment {
    @Id
    private Long id;

    @ManyToOne
    private Patient patient;

    private String treatmentType;
    private String treatmentDetails;

    @Column(name = "treatment_date")  // Optional if the column name is different from the field name
    private LocalDate treatmentDate;  // Add the treatmentDate field

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getTreatmentType() {
        return treatmentType;
    }

    public void setTreatmentType(String treatmentType) {
        this.treatmentType = treatmentType;
    }

    public String getTreatmentDetails() {
        return treatmentDetails;
    }

    public void setTreatmentDetails(String treatmentDetails) {
        this.treatmentDetails = treatmentDetails;
    }

    public LocalDate getTreatmentDate() {
        return treatmentDate;
    }

    public void setTreatmentDate(LocalDate treatmentDate) {
        this.treatmentDate = treatmentDate;
    }
}
