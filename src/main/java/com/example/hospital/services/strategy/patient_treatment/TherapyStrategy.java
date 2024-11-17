package com.example.hospital.services.strategy.patient_treatment;

import com.example.hospital.models.Patient;
import com.example.hospital.models.TherapyTreatment;
import com.example.hospital.repositories.TherapyTreatmentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TherapyStrategy implements TreatmentStrategy {

    @Autowired
    private TherapyTreatmentRepository therapyRepository;

    @Override
    public void applyTreatment(Patient patient, Object... args) {
        String therapyType = (String) args[0];
        int duration = (int) args[1];
        String frequency = (String) args[2];
        String notes = (String) args[3];

        TherapyTreatment treatment = new TherapyTreatment();
        treatment.setPatient(patient);
        treatment.setTherapyType(therapyType);
        treatment.setDuration(duration);
        treatment.setFrequency(frequency);
        treatment.setNotes(notes);

        therapyRepository.save(treatment);
    }
}
