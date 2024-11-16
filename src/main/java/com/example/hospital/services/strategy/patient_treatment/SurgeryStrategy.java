package com.example.hospital.services.strategy.patient_treatment;

import com.example.hospital.models.Patient;
import com.example.hospital.models.SurgeryTreatment;
import com.example.hospital.repositories.SurgeryTreatmentRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
@Component
public class SurgeryStrategy implements TreatmentStrategy {

    @Autowired
    private SurgeryTreatmentRepository surgeryRepository;

    @Override
    public void applyTreatment(Patient patient, Object... args) {
        String surgeryType = (String) args[0];
        String location = (String) args[1];
        String surgeon = (String) args[2];
        String date = (String) args[3];

        SurgeryTreatment treatment = new SurgeryTreatment();
        treatment.setPatient(patient);
        treatment.setSurgeryType(surgeryType);
        treatment.setLocation(location);
        treatment.setSurgeon(surgeon);
        treatment.setDate(date);

        surgeryRepository.save(treatment);
    }
}
