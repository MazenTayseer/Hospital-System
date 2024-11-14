package com.example.hospital.models;

import java.time.LocalDate;

import com.example.hospital.models.enums.TreatmentType;

import jakarta.persistence.*;

@Entity
public class PatientTreatment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @Enumerated(EnumType.STRING)
    private TreatmentType treatmentType;

    private String description;

    private String dateApplied;

    // Constructors
    public PatientTreatment() {}

    public PatientTreatment(Patient patient, TreatmentType treatmentType, String description) {
    this.patient = patient;
    this.treatmentType = treatmentType;
    this.description = description;
    this.dateApplied = LocalDate.now().toString();
}


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

    public TreatmentType getTreatmentType() {
        return treatmentType;
    }

    public void setTreatmentType(TreatmentType treatmentType) {
        this.treatmentType = treatmentType;
    }

    public String getdescription() {
        return description;
    }

    public void setdescription(String description) {
        this.description = description;
    }

    public String getDateApplied() {
        return dateApplied;
    }

    public void setDateApplied(String dateApplied) {
        this.dateApplied = dateApplied;
    }
}
