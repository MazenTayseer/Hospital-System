package com.example.hospital.models;

import jakarta.persistence.*;

@Entity
public class SurgeryTreatment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    private String surgeryType;
    private String location;
    private String surgeon;
    private String date;

    // Getters and setters

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

    public String getSurgeryType() {
        return surgeryType;
    }

    public void setSurgeryType(String surgeryType) {
        this.surgeryType = surgeryType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSurgeon() {
        return surgeon;
    }

    public void setSurgeon(String surgeon) {
        this.surgeon = surgeon;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
