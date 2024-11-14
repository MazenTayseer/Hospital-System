package com.example.hospital.services.strategy.patient_treatment;

import com.example.hospital.models.Patient;
import com.example.hospital.models.PatientTreatment;
import com.example.hospital.repositories.PatientTreatmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SurgeryStrategy implements TreatmentStrategy {

    @Autowired
    private PatientTreatmentRepository patientTreatmentRepository;

    @Override
    public void applyTreatment(Patient patient, String treatmentDetails) {
        PatientTreatment treatment = new PatientTreatment();
        treatment.setPatient(patient);
        treatment.setTreatmentType("Surgery");
        treatment.setTreatmentDetails(treatmentDetails);

        patientTreatmentRepository.save(treatment);
    }
}
