package com.example.hospital.services;

import com.example.hospital.models.Doctor;
import com.example.hospital.models.Patient;
import com.example.hospital.models.Prescription;
import com.example.hospital.repositories.DoctorRepository;
import com.example.hospital.repositories.PatientRepository;
import com.example.hospital.repositories.PrescriptionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PrescriptionService {

   private final PrescriptionRepository prescriptionRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    public PrescriptionService(PrescriptionRepository prescriptionRepository, PatientRepository patientRepository, DoctorRepository doctorRepository) {
        this.prescriptionRepository = prescriptionRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }


    public boolean validatePrescription(Long patientId, Long doctorId, String medicationName) {

      // Fetch the prescription from the repository
      Optional<Prescription> optionalPrescription = prescriptionRepository
          .findByPatientIdAndDoctorIdAndMedicationName(patientId, doctorId, medicationName);

      if (optionalPrescription.isPresent()) {

        Prescription prescription = optionalPrescription.get();

        // Check if the prescription is expired
        LocalDate expiryDate = prescription.getExpiryDate();
        LocalDate currentDate = LocalDate.now();

        if (expiryDate.isAfter(currentDate)) {
          return true; // Prescription is valid
        }
      }

      return false; // Invalid or expired prescription
    }

  public Prescription addPrescription(Long patientId, Long doctorId, String medicationName, int quantity, String issueDate, String expiryDate) {

        // Trim any unwanted leading/trailing whitespaces or newlines
        issueDate = issueDate.trim();
        expiryDate = expiryDate.trim();

        // Convert issueDate and expiryDate to LocalDate
        LocalDate issueDateConverted = LocalDate.parse(issueDate);
        LocalDate expiryDateConverted = LocalDate.parse(expiryDate);

        // Fetch the patient and doctor from the database
        Patient patient = patientRepository.findById(patientId)
                                          .orElseThrow(() -> new IllegalArgumentException("Patient not found"));
        Doctor doctor = doctorRepository.findById(doctorId)
                                        .orElseThrow(() -> new IllegalArgumentException("Doctor not found"));

        // Create the prescription
        Prescription prescription = new Prescription(patient, doctor, medicationName, quantity, issueDateConverted, expiryDateConverted);

        // Save the prescription
        return prescriptionRepository.save(prescription);
    }


  // Retrieve all prescriptions
  public List<Prescription> getAllPrescriptions() {
      return prescriptionRepository.findAll();
  }
}
