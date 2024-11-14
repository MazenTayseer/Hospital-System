package com.example.hospital.controllers;

import com.example.hospital.models.PatientTreatment;
import com.example.hospital.models.enums.TreatmentType;
import com.example.hospital.services.strategy.patient_treatment.TreatmentContext;
import com.example.hospital.services.strategy.patient_treatment.TreatmentStrategy;
import com.example.hospital.services.PatientTreatmentService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patients")
public class PatientTreatmentController {

    @Autowired
    private PatientTreatmentService patientTreatmentService;

    @Autowired
    private TreatmentContext treatmentContext;


 @PostMapping("/{patientId}/applyTreatment")
public void applyTreatment(@PathVariable Long patientId,
                           @RequestParam TreatmentType treatmentType,
                           @RequestParam String details) {
    // Determine the appropriate strategy based on the treatment type
    TreatmentStrategy strategy = treatmentContext.getStrategy();



    // Now, apply the treatment
    patientTreatmentService.applyTreatment(patientId, treatmentType, details, strategy);
}


    // Endpoint to retrieve all treatments for a patient
    @GetMapping("/{patientId}/treatments")
    public List<PatientTreatment> getAllTreatmentsForPatient(@PathVariable Long patientId) {
        return patientTreatmentService.getAllTreatmentsForPatient(patientId);
    }

}
