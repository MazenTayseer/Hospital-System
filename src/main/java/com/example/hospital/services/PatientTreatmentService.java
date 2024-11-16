package com.example.hospital.services;

import com.example.hospital.models.Patient;
import com.example.hospital.models.PatientTreatment;
import com.example.hospital.models.TreatmentRequest;
import com.example.hospital.repositories.PatientRepository;
import com.example.hospital.repositories.PatientTreatmentRepository;
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

    public void assignTreatmentToPatient(TreatmentRequest request) {
        Patient patient = getPatientFromRequest(request);

        switch (request.getTreatmentType()) {
            case MEDICATION:
                strategyFactory.getMedicationStrategy()
                        .applyTreatment(patient, request.getMedicationName(), request.getDosage(),
                                request.getDuration(), request.getFrequency());
                break;

            case SURGERY:
                strategyFactory.getSurgeryStrategy()
                        .applyTreatment(patient, request.getSurgeryType(), request.getLocation(),
                                request.getSurgeon(), request.getDate());
                break;

            case THERAPY:
                strategyFactory.getTherapyStrategy()
                        .applyTreatment(patient, request.getTherapyType(), request.getDuration(),
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
