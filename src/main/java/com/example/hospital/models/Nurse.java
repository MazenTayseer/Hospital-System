package com.example.hospital.models;

import java.util.HashSet;

import com.example.hospital.models.enums.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "nurses")
@JsonIgnoreProperties({ "shifts", "patients" })
public class Nurse extends User {

    @OneToMany(mappedBy = "nurse", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Shift> shifts = new HashSet<>();

    @OneToMany(mappedBy = "assignedNurse", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Patient> patients = new HashSet<>();

    public Nurse() {
        super();
    }

    public Nurse(
            String firstName,
            String lastName,
            String email,
            String password,
            String phone,
            int age,
            Gender gender) {
        super(firstName, lastName, email, password, phone, age, gender);
    }

    public Set<Shift> getShifts() {
        return shifts;
    }

    public void setShifts(Set<Shift> shifts) {
        this.shifts = shifts;
    }

    public void addShift(Shift shift) {
        shifts.add(shift);
        shift.setNurse(this);
    }

    public void removeShift(Shift shift) {
        shifts.remove(shift);
        shift.setNurse(null);
    }

    public void addPatient(Patient patient) {
        patients.add(patient);
        patient.setAssignedNurse(this);
    }

    public void removePatient(Patient patient) {
        patients.remove(patient);
        patient.setAssignedNurse(null);
    }
}
