package com.example.hospital.controllers;

import com.example.hospital.models.dto.MedicationTreatmentDTO;
import com.example.hospital.models.dto.SurgeryTreatmentDTO;
import com.example.hospital.models.dto.TherapyTreatmentDTO;
import com.example.hospital.models.request.TreatmentRequest;
import com.example.hospital.services.PatientTreatmentService;

import jakarta.validation.Valid;

import java.util.List;

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

    @GetMapping("/patient/{patientId}/medications")
    public ResponseEntity<List<MedicationTreatmentDTO>> getMedicationTreatmentsForPatient(@PathVariable Long patientId) {
      List<MedicationTreatmentDTO> treatments = treatmentService.getMedicationTreatmentsForPatient(patientId);
      return ResponseEntity.ok(treatments);
}

    // Get all surgery treatments for a patient
    @GetMapping("/patient/{patientId}/surgeries")
    public ResponseEntity<List<SurgeryTreatmentDTO>> getSurgeryTreatmentsForPatient(@PathVariable Long patientId) {
      List<SurgeryTreatmentDTO> treatments = treatmentService.getSurgeryTreatmentsForPatient(patientId);
      return ResponseEntity.ok(treatments);
}

    // Get all therapy treatments for a patient
    @GetMapping("/patient/{patientId}/therapies")
    public ResponseEntity<List<TherapyTreatmentDTO>> getTherapyTreatmentsForPatient(@PathVariable Long patientId) {
      List<TherapyTreatmentDTO> treatments = treatmentService.getTherapyTreatmentsForPatient(patientId);
      return ResponseEntity.ok(treatments);
}
}
