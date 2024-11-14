package com.example.hospital.services;

import com.example.hospital.models.Patient;
import com.example.hospital.models.PatientTreatment;
import com.example.hospital.repositories.PatientRepository;
import com.example.hospital.repositories.PatientTreatmentRepository;
import com.example.hospital.services.strategy.patient_treatment.TreatmentContext;
import com.example.hospital.services.strategy.patient_treatment.TreatmentStrategy;
import com.example.hospital.models.enums.TreatmentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PatientTreatmentService {

    @Autowired
    private PatientTreatmentRepository patientTreatmentRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private TreatmentContext treatmentContext;

    // Apply treatment to a patient by passing strategy as parameter
    public void applyTreatment(Long patientId, TreatmentType treatmentType, String details, TreatmentStrategy strategy) {
        Optional<Patient> patientOpt = patientRepository.findById(patientId);
        if (patientOpt.isPresent()) {
            Patient patient = patientOpt.get();

            // Set the appropriate strategy dynamically
            treatmentContext.setStrategy(strategy);

            // Apply the treatment using the strategy
            treatmentContext.applyTreatment(patient);

            // Create and save a PatientTreatment record
            PatientTreatment treatmentRecord = new PatientTreatment(patient, treatmentType, details);
            treatmentRecord.setDateApplied(LocalDate.now().toString());
            patientTreatmentRepository.save(treatmentRecord);
        } else {
            throw new RuntimeException("Patient not found with ID: " + patientId);
        }
    }

    // Retrieve all treatments for a patient
    public List<PatientTreatment> getAllTreatmentsForPatient(Long patientId) {
        return patientTreatmentRepository.findByPatientId(patientId);
    }
}
