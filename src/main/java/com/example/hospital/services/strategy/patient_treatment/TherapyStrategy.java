package com.example.hospital.services.strategy.patient_treatment;

import com.example.hospital.models.Patient;
import com.example.hospital.models.PatientTreatment;
import com.example.hospital.models.enums.TreatmentType;
import com.example.hospital.repositories.PatientTreatmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TherapyStrategy implements TreatmentStrategy {

    @Autowired
    private PatientTreatmentRepository patientTreatmentRepository;

    @Override
    public void applyTreatment(Patient patient, Object... args) {
        String therapyType = (String) args[0];
        int duration = (int) args[1];
        String frequency = (String) args[2];
        String notes = (String) args[3];

        PatientTreatment treatment = new PatientTreatment();
        treatment.setPatient(patient);
        treatment.setTreatmentType(TreatmentType.THERAPY);
        treatment.setdescription(String.format("Therapy: %s, Duration: %d days, Frequency: %s, Notes: %s",
                therapyType, duration, frequency, notes));

        patientTreatmentRepository.save(treatment);
    }
}
