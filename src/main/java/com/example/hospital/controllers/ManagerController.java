package com.example.hospital.controllers;

import com.example.hospital.dto.UserDto;
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
    public ResponseEntity<Doctor> createDoctor(@RequestBody UserDto<Doctor> request) {
        Doctor createdUser = (Doctor) managerService.createUser(request);
        return ResponseEntity.ok(createdUser);
    }

    @PostMapping("/create-nurse")
    public ResponseEntity<Nurse> createNurse(@RequestBody UserDto<Nurse> request) {
        Nurse createdUser = (Nurse) managerService.createUser(request);
        return ResponseEntity.ok(createdUser);
    }

    @PostMapping("/create-patient")
    public ResponseEntity<Patient> createPatient(@RequestBody UserDto<Patient> request) {
        Patient createdUser = (Patient) managerService.createUser(request);
        return ResponseEntity.ok(createdUser);
    }

    @PostMapping("/create-volunteer")
    public ResponseEntity<Volunteer> createVolunteer(@RequestBody UserDto<Patient> request) {
        Volunteer createdUser = (Volunteer) managerService.createUser(request);
        return ResponseEntity.ok(createdUser);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        managerService.deleteUser(userId);
        return ResponseEntity.noContent().build(); 
    }
}
