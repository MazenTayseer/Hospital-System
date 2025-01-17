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

    private String status; // "AVAILABLE" or "OCCUPIED"

    // Constructor
    public Room() {
        this.status = "AVAILABLE"; // Default status
    }

    // Getters and Setters
    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public List<Nurse> getNurses() {
        return nurses;
    }

    public void setNurses(List<Nurse> nurses) {
        this.nurses = nurses;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Helper methods to check if patients/nurses can be assigned
    public boolean canAssignDoctor() {
        return doctor == null && status.equals("AVAILABLE");
    }

    public boolean canAssignNurse() {
        return nurses.size() < 4 && status.equals("AVAILABLE");
    }

    public boolean canAssignPatient() {
        return patients.size() < 2 && status.equals("AVAILABLE");
    }

    // Additional methods to assign/remove patients/nurses
    public void assignPatient(Patient patient) {
        if (canAssignPatient()) {
            patients.add(patient);
            if (patients.size() == 2) {
                this.status = "OCCUPIED"; // Mark room as occupied when the max number of patients is reached
            }
        }
    }

    public void removePatient(Patient patient) {
        if (patients.contains(patient)) {
            patients.remove(patient);
            this.status = "AVAILABLE"; // Mark room as available when there are no patients
        }
    }

    public void assignNurse(Nurse nurse) {
        if (canAssignNurse()) {
            nurses.add(nurse);
        }
    }

    public void removeNurse(Nurse nurse) {
        if (nurses.contains(nurse)) {
            nurses.remove(nurse);
        }
    }
}
