package com.example.hospital.controllers.users;

import com.example.hospital.models.Doctor;
import com.example.hospital.models.Nurse;
import com.example.hospital.models.Patient;
import com.example.hospital.services.users.ManagerService;

import org.springframework.http.HttpStatus;
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
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PostMapping("/create-nurse")
    public ResponseEntity<Nurse> createNurse(@RequestBody Nurse nurse) {
        Nurse createdUser = (Nurse) managerService.createUser(nurse);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PostMapping("/create-patient")
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
        Patient createdUser = (Patient) managerService.createUser(patient);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
}
