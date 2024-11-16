package com.example.hospital.services.strategy.patient_treatment;

import com.example.hospital.models.MedicationTreatment;
import com.example.hospital.models.Patient;
import com.example.hospital.repositories.MedicationTreatmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MedicationStrategy implements TreatmentStrategy {

    @Autowired
    private MedicationTreatmentRepository medicationRepository;

    @Override
    public void applyTreatment(Patient patient, Object... args) {
        String medicationName = (String) args[0];
        String dosage = (String) args[1];
        int duration = (int) args[2];
        String frequency = (String) args[3];

        MedicationTreatment treatment = new MedicationTreatment();
        treatment.setPatient(patient);
        treatment.setMedicationName(medicationName);
        treatment.setDosage(dosage);
        treatment.setDuration(duration);
        treatment.setFrequency(frequency);

        medicationRepository.save(treatment);
    }
}
