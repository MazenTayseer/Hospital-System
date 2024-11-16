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
    private TreatmentContext treatmentContext; // Inject the context

    public void assignTreatmentToPatient(TreatmentRequest request) {
        Patient patient = getPatientFromRequest(request);

        // Set the appropriate strategy in the context
        switch (request.getTreatmentType()) {
            case MEDICATION:
                treatmentContext.setStrategy(strategyFactory.getMedicationStrategy());
                treatmentContext.applyTreatment(patient, request.getMedicationName(), request.getDosage(),
                        request.getDuration(), request.getFrequency());
                break;

            case SURGERY:
                treatmentContext.setStrategy(strategyFactory.getSurgeryStrategy());
                treatmentContext.applyTreatment(patient, request.getSurgeryType(), request.getLocation(),
                        request.getSurgeon(), request.getDate());
                break;

            case THERAPY:
                treatmentContext.setStrategy(strategyFactory.getTherapyStrategy());
                treatmentContext.applyTreatment(patient, request.getTherapyType(), request.getDuration(),
                        request.getFrequency(), request.getTherapyNotes());
                break;

            default:
                throw new IllegalArgumentException("Invalid treatment type");
        }
    }

    private Patient getPatientFromRequest(TreatmentRequest request) {
        return patientRepository.findById(request.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));
    }

    public List<PatientTreatment> getTreatmentsForPatient(Long patientId) {
        patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        return treatmentRepository.findByPatientId(patientId);
    }
}
