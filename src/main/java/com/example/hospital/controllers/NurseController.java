package com.example.hospital.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.hospital.models.Nurse;
import com.example.hospital.models.Patient;
import com.example.hospital.services.NurseService;
import com.example.hospital.dto.AssignPatientDto;

import java.util.List;

@RestController
@RequestMapping("/api/nurses")
public class NurseController {

    private final NurseService nurseService;

    @Autowired
    public NurseController(NurseService nurseService) {
        this.nurseService = nurseService;
    }

    /**
     * Get all nurses
     */
    @GetMapping
    public ResponseEntity<List<Nurse>> getAllNurses() {
        List<Nurse> nurses = nurseService.getAllNurses();
        return ResponseEntity.ok(nurses);
    }

    /**
     * Get nurse by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Nurse> getNurseById(@PathVariable Long id) {
        Nurse nurse = nurseService.getNurseById(id);
        if (nurse != null) {
            return ResponseEntity.ok(nurse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Assign a patient to a nurse
     */
    @PostMapping("/{nurseId}/assign-patient")
    public ResponseEntity<Patient> assignPatientToNurse(@PathVariable Long nurseId,
            @RequestBody AssignPatientDto assignPatientDto) {
        Patient patient = nurseService.assignPatientToNurse(nurseId, assignPatientDto.getPatientId());
        if (patient != null) {
            return ResponseEntity.ok(patient);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Get all patients assigned to a nurse
     */
    @GetMapping("/{nurseId}/patients")
    public ResponseEntity<List<Patient>> getPatientsOfNurse(@PathVariable Long nurseId) {
        List<Patient> patients = nurseService.getPatientsOfNurse(nurseId);
        return ResponseEntity.ok(patients);
    }
}
