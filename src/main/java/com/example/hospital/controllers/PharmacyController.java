package com.example.hospital.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.hospital.services.facade.PharmacyFacade;

@RestController
@RequestMapping("/api/pharmacy")
public class PharmacyController {

    @Autowired
    private final PharmacyFacade pharmacyFacade;

    public PharmacyController(PharmacyFacade pharmacyFacade) {
        this.pharmacyFacade = pharmacyFacade;
    }

    @PostMapping("/dispense")
    public ResponseEntity<String> dispenseMedication(
            @RequestParam Long patientId,
            @RequestParam Long doctorId,
            @RequestParam String medicationName,
            @RequestParam int quantity) {
        try {
            pharmacyFacade.dispenseMedication(patientId, doctorId, medicationName, quantity);
            return ResponseEntity.ok("Medication dispensed successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
