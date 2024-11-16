package com.example.hospital.services.strategy.patient_treatment;

import com.example.hospital.models.Patient;
import com.example.hospital.models.PatientTreatment;
import com.example.hospital.models.enums.TreatmentType;
import com.example.hospital.repositories.PatientTreatmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MedicationStrategy implements TreatmentStrategy {

    @Autowired
    private PatientTreatmentRepository patientTreatmentRepository;

    @Override
    public void applyTreatment(Patient patient, Object... args) {
        String medicationName = (String) args[0];
        String dosage = (String) args[1];
        int duration = (int) args[2];
        String frequency = (String) args[3];

        PatientTreatment treatment = new PatientTreatment();
        treatment.setPatient(patient);
        treatment.setTreatmentType(TreatmentType.MEDICATION);
        treatment.setdescription(String.format("Medication: %s, Dosage: %s, Duration: %d days, Frequency: %s",
                medicationName, dosage, duration, frequency));

        patientTreatmentRepository.save(treatment);
    }
}
