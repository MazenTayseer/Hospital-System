package com.example.hospital.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;

    @OneToOne
    private Doctor doctor;

    @ManyToMany
    private List<Nurse> nurses = new ArrayList<>();

    @ManyToMany
    private List<Patient> patients = new ArrayList<>();

    public boolean canAssignDoctor() {
        return doctor == null;
    }

    public boolean canAssignNurse() {
        return nurses.size() < 4;
    }

    public boolean canAssignPatient() {
        return patients.size() < 2;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public List<Nurse> getNurses() {
        return nurses;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
