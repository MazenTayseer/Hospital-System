package com.example.hospital.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull(message = "Rating is required")
    private Double rating;

    @Column(nullable = true)
    private String comment;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    @NotNull(message = "Doctor is required for the review")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    @NotNull(message = "Patient is required for the review")
    private Patient patient;

    public Review() {}

    public Review(
        Double rating, 
        String comment,
        Doctor doctor,
        Patient patient
    ) {
        this.rating = rating;
        this.comment = comment;
        this.doctor = doctor;
        this.patient = patient;
    }

    public Long getId() { return this.id; }
    public Double getRating() { return this.rating; }
    public String getComment() { return this.comment; }
    public Doctor getDoctor() { return this.doctor; }
    public Patient getPatient() { return this.patient; }

    public void setDoctor(Doctor doctor) { this.doctor = doctor; }
    public void setPatient(Patient patient) { this.patient = patient; }
}
