package com.example.hospital.controllers;

import com.example.hospital.models.PatientTreatment;
import com.example.hospital.models.dto.PatientTreatmentDTO;
import com.example.hospital.models.request.TreatmentRequest;
import com.example.hospital.services.PatientTreatmentService;

import jakarta.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/treatment")
public class PatientTreatmentController {

    @Autowired
    private PatientTreatmentService treatmentService;

    // Apply Medication treatment
    @PostMapping("/apply/medication")
    public ResponseEntity<String> applyMedicationTreatment(@Valid @RequestBody TreatmentRequest treatmentRequest) {
        treatmentService.assignMedicationTreatmentToPatient(treatmentRequest);
        return ResponseEntity.ok("Medication treatment applied successfully");
    }

    // Apply Surgery treatment
    @PostMapping("/apply/surgery")
    public ResponseEntity<String> applySurgeryTreatment(@Valid @RequestBody TreatmentRequest treatmentRequest) {
        treatmentService.assignSurgeryTreatmentToPatient(treatmentRequest);
        return ResponseEntity.ok("Surgery treatment applied successfully");
    }

    // Apply Therapy treatment
    @PostMapping("/apply/therapy")
    public ResponseEntity<String> applyTherapyTreatment(@Valid @RequestBody TreatmentRequest treatmentRequest) {
        treatmentService.assignTherapyTreatmentToPatient(treatmentRequest);
        return ResponseEntity.ok("Therapy treatment applied successfully");
    }

    // Get all treatments for a patient
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<PatientTreatmentDTO>> getTreatmentsForPatient(@PathVariable Long patientId) {
        List<PatientTreatment> treatments = treatmentService.getTreatmentsForPatient(patientId);
        List<PatientTreatmentDTO> treatmentDTOs = treatments.stream()
            .map(treatment -> new PatientTreatmentDTO(
                treatment.getId(),
                treatment.getTreatmentType(),
                treatment.getdescription(),
                treatment.getDateApplied()))
            .collect(Collectors.toList());
        return ResponseEntity.ok(treatmentDTOs);
    }
}
