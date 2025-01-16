package com.example.hospital.services;

import com.example.hospital.models.Prescription;
import com.example.hospital.repositories.PrescriptionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;

    public PrescriptionService(PrescriptionRepository prescriptionRepository) {
        this.prescriptionRepository = prescriptionRepository;
    }

    public boolean validatePrescription(Long patientId, Long doctorId, String medicationName) {
      System.out.println("Starting validation for patientId: " + patientId + ", doctorId: " + doctorId + ", medicationName: " + medicationName);

      // Fetch the prescription from the repository
      Optional<Prescription> optionalPrescription = prescriptionRepository.findByPatientIdAndDoctorIdAndMedicationName(patientId, doctorId, medicationName);

      if (optionalPrescription.isPresent()) {
          System.out.println("Prescription found for the given patient, doctor, and medication.");

          Prescription prescription = optionalPrescription.get();
          System.out.println("Prescription details: " + prescription);

          // Check if the prescription is expired
          LocalDate expiryDate = prescription.getExpiryDate();
          LocalDate currentDate = LocalDate.now();

          System.out.println("Expiry Date: " + expiryDate);
          System.out.println("Current Date: " + currentDate);

          if (expiryDate.isAfter(currentDate)) {
              System.out.println("Prescription is valid and not expired.");
              return true; // Prescription is valid
          } else {
              System.out.println("Prescription is expired.");
          }
      } else {
          System.out.println("No prescription found for the given patient, doctor, and medication.");
      }

      System.out.println("Prescription is invalid or expired.");
      return false; // Invalid or expired prescription
  }
}
