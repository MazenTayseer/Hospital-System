package com.example.hospital.controllers;

import com.example.hospital.models.Doctor;
import com.example.hospital.models.Nurse;
import com.example.hospital.models.Patient;
import com.example.hospital.models.Volunteer;
import com.example.hospital.services.ManagerService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/managers")
public class ManagerController {

    private final ManagerService managerService;

    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @PostMapping("/create-doctor")
    public ResponseEntity<Doctor> createDoctor(@RequestBody Doctor doctor) {
        Doctor createdUser = (Doctor) managerService.createUser(doctor);
        return ResponseEntity.ok(createdUser);
    }

    @PostMapping("/create-nurse")
    public ResponseEntity<Nurse> createNurse(@RequestBody Nurse nurse) {
        Nurse createdUser = (Nurse) managerService.createUser(nurse);
        return ResponseEntity.ok(createdUser);
    }

    @PostMapping("/create-patient")
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
        Patient createdUser = (Patient) managerService.createUser(patient);
        return ResponseEntity.ok(createdUser);
    }

    @PostMapping("/create-volunteer")
    public ResponseEntity<Volunteer> createVolunteer(@RequestBody Volunteer volunteer) {
        Volunteer createdUser = (Volunteer) managerService.createUser(volunteer);
        return ResponseEntity.ok(createdUser);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        managerService.deleteUser(userId);
        return ResponseEntity.noContent().build(); 
    }
}
