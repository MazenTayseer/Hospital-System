package com.example.hospital.controllers;

import com.example.hospital.models.PatientTreatment;
import com.example.hospital.models.TreatmentRequest;
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

    @PostMapping("/apply")
    public ResponseEntity<String> applyTreatment(@Valid @RequestBody TreatmentRequest treatmentRequest) {
      treatmentService.assignTreatmentToPatient(treatmentRequest);
      return ResponseEntity.ok("Treatment applied successfully");
    }


    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<PatientTreatment>> getTreatmentsForPatient(@PathVariable Long patientId) {
        List<PatientTreatment> treatments = treatmentService.getTreatmentsForPatient(patientId);
        return ResponseEntity.ok(treatments);
    }
}
