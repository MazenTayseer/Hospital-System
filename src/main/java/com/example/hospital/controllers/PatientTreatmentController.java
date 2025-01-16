package com.example.hospital.controllers;

import com.example.hospital.models.MedicationTreatment;
import com.example.hospital.models.SurgeryTreatment;
import com.example.hospital.models.TherapyTreatment;
import com.example.hospital.models.dto.MedicationTreatmentDTO;
import com.example.hospital.models.dto.SurgeryTreatmentDTO;
import com.example.hospital.models.dto.TherapyTreatmentDTO;
import com.example.hospital.models.request.TreatmentRequest;
import com.example.hospital.services.PatientTreatmentService;

import jakarta.validation.Valid;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/treatment")
@CrossOrigin(origins = "http://localhost:5173")
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

    // Get all medication treatments for a patient
    @GetMapping("/patient/{patientId}/medications")
    public ResponseEntity<List<MedicationTreatmentDTO>> getMedicationTreatmentsForPatient(
            @PathVariable Long patientId) {
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

    // Generate report for medication treatment
   @GetMapping("/report/medication/{treatmentId}")
public ResponseEntity<Map<String, Object>> getMedicationTreatmentReport(@PathVariable Long treatmentId) {
    MedicationTreatment treatment = treatmentService.getMedicationTreatmentById(treatmentId);

    // Create a JSON-like structure
    Map<String, Object> report = new LinkedHashMap<>();
    report.put("title", "Treatment Report");
    report.put("hospital", "Ain Shams Charity Hospital");
    report.put("patientId", treatment.getPatient().getId());
    report.put("medicationName", treatment.getMedicationName());
    report.put("dosage", treatment.getDosage());
    report.put("duration", treatment.getDuration());
    report.put("frequency", treatment.getFrequency());
    report.put("footer", "Report Generated Successfully!");

    // Return the report as a JSON object
    return ResponseEntity.ok(report);
}


    @GetMapping("/report/surgery/{treatmentId}")
    public ResponseEntity<Map<String, Object>> getSurgeryTreatmentReport(@PathVariable Long treatmentId) {
        SurgeryTreatment treatment = treatmentService.getSurgeryTreatmentById(treatmentId);

        // Create a JSON-friendly structure for the report
        Map<String, Object> report = new LinkedHashMap<>();
        report.put("title", "Treatment Report");
        report.put("hospital", "Ain Shams Charity Hospital");
        report.put("patientId", treatment.getPatient().getId());
        report.put("surgeryType", treatment.getSurgeryType());
        report.put("location", treatment.getLocation());
        report.put("surgeon", treatment.getSurgeon());
        report.put("date", treatment.getDate());
        report.put("footer", "Report Generated Successfully!");

        // Return the report as a JSON object
        return ResponseEntity.ok(report);
    }

    @GetMapping("/report/therapy/{treatmentId}")
    public ResponseEntity<Map<String, Object>> getTherapyTreatmentReport(@PathVariable Long treatmentId) {
        TherapyTreatment treatment = treatmentService.getTherapyTreatmentById(treatmentId);

        // Create a JSON-friendly structure for the report
        Map<String, Object> report = new LinkedHashMap<>();
        report.put("title", "Treatment Report");
        report.put("hospital", "Ain Shams Charity Hospital");
        report.put("patientId", treatment.getPatient().getId());
        report.put("therapyType", treatment.getTherapyType());
        report.put("duration", treatment.getDuration() + " days");
        report.put("frequency", treatment.getFrequency());
        report.put("notes", treatment.getNotes());
        report.put("footer", "Report Generated Successfully!");

        // Return the report as a JSON object
        return ResponseEntity.ok(report);
    }
}
