package com.example.hospital.controllers;

import com.example.hospital.models.Prescription;
import com.example.hospital.repositories.DoctorRepository;
import com.example.hospital.repositories.PatientRepository;
import com.example.hospital.services.PrescriptionService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {

  private final PrescriptionService prescriptionService;


    public PrescriptionController(PrescriptionService prescriptionService,PatientRepository patientRepository,DoctorRepository doctorRepository) {
        this.prescriptionService = prescriptionService;

    }

    @PostMapping
    public ResponseEntity<Prescription> addPrescription(
            @RequestParam Long patientId,
            @RequestParam Long doctorId,
            @RequestParam String medicationName,
            @RequestParam int quantity,
            @RequestParam String issueDate,
            @RequestParam String expiryDate) {

        Prescription savedPrescription = prescriptionService.addPrescription(patientId, doctorId, medicationName, quantity, issueDate, expiryDate);
        return ResponseEntity.ok(savedPrescription);
    }




    @GetMapping
    public ResponseEntity<List<Prescription>> getAllPrescriptions() {
        List<Prescription> prescriptions = prescriptionService.getAllPrescriptions();
        return ResponseEntity.ok(prescriptions);
    }
}
