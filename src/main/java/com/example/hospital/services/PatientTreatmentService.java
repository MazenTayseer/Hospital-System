package com.example.hospital.services;

import com.example.hospital.models.Patient;
import com.example.hospital.models.PatientTreatment;
import com.example.hospital.repositories.PatientRepository;
import com.example.hospital.repositories.PatientTreatmentRepository;
import com.example.hospital.services.strategy.patient_treatment.MedicationStrategy;
import com.example.hospital.services.strategy.patient_treatment.SurgeryStrategy;
import com.example.hospital.services.strategy.patient_treatment.TherapyStrategy;
import com.example.hospital.services.strategy.patient_treatment.TreatmentContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientTreatmentService {

    @Autowired
    private TreatmentContext treatmentContext;  // The context that holds the strategy

    @Autowired
    private MedicationStrategy medicationStrategy;  // Concrete strategy for medication

    @Autowired
    private SurgeryStrategy surgeryStrategy;  // Concrete strategy for surgery

    @Autowired
    private TherapyStrategy therapyStrategy; // Concrete strategy for therapy

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientTreatmentRepository patientTreatmentRepository; // Repository to fetch treatments

    // This method applies the treatment based on the treatment type
    public void applyTreatment(Long patientId, String treatmentType, String treatmentDetails) {
        // Retrieve the patient (you can get this from the repository as shown in the previous steps)
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        // Set the appropriate strategy based on the treatment type
        switch (treatmentType) {
            case "Medication":
                treatmentContext.setStrategy(medicationStrategy);
                break;
            case "Surgery":
                treatmentContext.setStrategy(surgeryStrategy);
                break;
            case "Therapy":
                treatmentContext.setStrategy(therapyStrategy);
                break;
            default:
                throw new IllegalArgumentException("Unknown treatment type: " + treatmentType);
        }

        // Apply the treatment using the selected strategy
        treatmentContext.applyTreatment(patient, treatmentDetails);
    }

    // Method to fetch all treatments for a specific patient
    public List<PatientTreatment> getTreatmentsByPatient(Long patientId) {
        // Retrieve the list of treatments for the patient using the PatientTreatmentRepository
        return patientTreatmentRepository.findByPatientId(patientId);
    }
}
