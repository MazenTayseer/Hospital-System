package com.example.hospital.services.strategy.patient_treatment;

import com.example.hospital.models.Patient;
import com.example.hospital.models.PatientTreatment;
import com.example.hospital.models.enums.TreatmentType;
import com.example.hospital.repositories.PatientTreatmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SurgeryStrategy implements TreatmentStrategy {

    @Autowired
    private PatientTreatmentRepository patientTreatmentRepository;

    private String surgeryType;
    private String location;
    private String surgeon;
    private String date;

    // Setters
    public void setSurgeryType(String surgeryType) { this.surgeryType = surgeryType; }
    public void setLocation(String location) { this.location = location; }
    public void setSurgeon(String surgeon) { this.surgeon = surgeon; }
    public void setDate(String date) { this.date = date; }

    @Override
    public void applyTreatment(Patient patient) {
        String description = "Surgery: " + surgeryType + ", Location: " + location +
                             ", Surgeon: " + surgeon + ", Date: " + date;
        PatientTreatment treatment = new PatientTreatment(patient,TreatmentType.SURGERY, description );
        patientTreatmentRepository.save(treatment);
    }
}
