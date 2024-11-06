package com.example.hospital.models;

import jakarta.persistence.*;

@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double rating;

    @Column(nullable = true)
    private String comment;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    @Column(nullable = false)
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    @Column(nullable = false)
    private Patient patient;

    public Review() {}

    public Long getId() { return this.id; }
    public Double getRating() { return this.rating; }
    public Doctor getDoctor() { return this.doctor; }
    public Patient getPatient() { return this.patient; }

    public void setDoctor(Doctor doctor) { this.doctor = doctor; }
    public void setPatient(Patient patient) { this.patient = patient; }
}
