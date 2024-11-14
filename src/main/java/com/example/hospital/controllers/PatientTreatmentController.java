package com.example.hospital.controllers;

import com.example.hospital.models.PatientTreatment;
import com.example.hospital.services.PatientTreatmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




import java.util.List;

@RestController
@RequestMapping("/api/treatments")
public class PatientTreatmentController {

  private static final Logger logger = LoggerFactory.getLogger(PatientTreatmentController.class);

  @Autowired
  private PatientTreatmentService patientTreatmentService;

  /**
   * Endpoint to apply a treatment to a patient based on the treatment type.
   *
   * @param treatment The PatientTreatment object containing details for the treatment.
   * @return Response indicating whether the treatment was applied successfully.
   */
  @PostMapping("/apply")
  public ResponseEntity<String> applyTreatment(@RequestBody PatientTreatment treatment) {
      try {
          // Log the request details for debugging purposes
          logger.info("Applying treatment for patient ID: {}, Treatment Type: {}, Treatment Details: {}",
                  treatment.getPatient().getId(),
                  treatment.getTreatmentType(),
                  treatment.getTreatmentDetails());

          // Apply the treatment using the service layer
          patientTreatmentService.applyTreatment(treatment.getPatient().getId(),
                                                 treatment.getTreatmentType(),
                                                 treatment.getTreatmentDetails());

          logger.info("Treatment applied successfully for patient ID: {}", treatment.getPatient().getId());
          return ResponseEntity.ok("Treatment applied successfully.");

      } catch (IllegalArgumentException e) {
          logger.error("Bad request error: {}", e.getMessage(), e);
          return ResponseEntity.badRequest().body("Error: " + e.getMessage());
      } catch (Exception e) {
          logger.error("Unexpected error occurred while applying treatment: {}", e.getMessage(), e);
          return ResponseEntity.status(500).body("An unexpected error occurred.");
      }
  }
    /**
     * Endpoint to get all treatments applied to a specific patient.
     *
     * @param patientId The ID of the patient.
     * @return A list of treatments applied to the patient.
     */
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<PatientTreatment>> getTreatmentsByPatient(@PathVariable Long patientId) {
        List<PatientTreatment> treatments = patientTreatmentService.getTreatmentsByPatient(patientId);

        if (treatments.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(treatments);
    }
}
