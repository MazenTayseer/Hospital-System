package com.example.hospital.services;

import com.example.hospital.models.Patient;
import com.example.hospital.models.PatientTreatment;
import com.example.hospital.models.request.TreatmentRequest;
import com.example.hospital.repositories.PatientRepository;
import com.example.hospital.repositories.PatientTreatmentRepository;
import com.example.hospital.services.strategy.patient_treatment.TreatmentContext;
import com.example.hospital.services.strategy.patient_treatment.TreatmentStrategyFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientTreatmentService {

    @Autowired
    private PatientTreatmentRepository treatmentRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private TreatmentStrategyFactory strategyFactory;

    @Autowired
    private TreatmentContext treatmentContext;

    // Apply Medication treatment
    public void assignMedicationTreatmentToPatient(TreatmentRequest request) {
        Patient patient = getPatientFromRequest(request);

        // Set strategy in the context
        treatmentContext.setStrategy(strategyFactory.getMedicationStrategy());

        // Apply the treatment with relevant details
        treatmentContext.applyTreatment(patient, request.getMedicationName(),
                request.getDosage(), request.getDuration(), request.getFrequency());
    }

    // Apply Surgery treatment
    public void assignSurgeryTreatmentToPatient(TreatmentRequest request) {
        Patient patient = getPatientFromRequest(request);

        // Set strategy in the context
        treatmentContext.setStrategy(strategyFactory.getSurgeryStrategy());

        // Apply the treatment with relevant details
        treatmentContext.applyTreatment(patient, request.getSurgeryType(),
                request.getLocation(), request.getSurgeon(), request.getDate());
    }

    // Apply Therapy treatment
    public void assignTherapyTreatmentToPatient(TreatmentRequest request) {
        Patient patient = getPatientFromRequest(request);

        // Set strategy in the context
        treatmentContext.setStrategy(strategyFactory.getTherapyStrategy());

        // Apply the treatment with relevant details
        treatmentContext.applyTreatment(patient, request.getTherapyType(),
                request.getDuration(), request.getFrequency(), request.getTherapyNotes());
    }

    private Patient getPatientFromRequest(TreatmentRequest request) {
        return patientRepository.findById(request.getPatientId())
            .orElseThrow(() -> new RuntimeException("Patient not found"));
    }

    // Get treatments for a patient
    public List<PatientTreatment> getTreatmentsForPatient(Long patientId) {
        patientRepository.findById(patientId)
            .orElseThrow(() -> new RuntimeException("Patient not found"));
        return treatmentRepository.findByPatientId(patientId);
    }
}
