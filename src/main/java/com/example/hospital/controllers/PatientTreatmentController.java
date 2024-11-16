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

    @PostMapping("/apply")
    public ResponseEntity<String> applyTreatment(@Valid @RequestBody TreatmentRequest treatmentRequest) {
      treatmentService.assignTreatmentToPatient(treatmentRequest);
      return ResponseEntity.ok("Treatment applied successfully");
    }


 @GetMapping("/patient/{patientId}")
public ResponseEntity<List<PatientTreatmentDTO>> getTreatmentsForPatient(@PathVariable Long patientId) {
    List<PatientTreatment> treatments = treatmentService.getTreatmentsForPatient(patientId);
    // Map PatientTreatment entities to PatientTreatmentDTO
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
