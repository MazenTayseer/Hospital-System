package com.example.hospital.services.strategy.patient_treatment;

import com.example.hospital.models.Patient;
import com.example.hospital.models.PatientTreatment;
import com.example.hospital.models.enums.TreatmentType;  // Import TreatmentType enum
import com.example.hospital.repositories.PatientTreatmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TherapyStrategy implements TreatmentStrategy {

    @Autowired
    private PatientTreatmentRepository patientTreatmentRepository;

    private String therapyType;
    private int duration;
    private String frequency;
    private String notes;

    // Setters
    public void setTherapyType(String therapyType) { this.therapyType = therapyType; }
    public void setDuration(int duration) { this.duration = duration; }
    public void setFrequency(String frequency) { this.frequency = frequency; }
    public void setNotes(String notes) { this.notes = notes; }

    @Override
    public void applyTreatment(Patient patient) {
        // Create description string for therapy treatment
        String description = "Therapy: " + therapyType + ", Duration: " + duration +
                             " days, Frequency: " + frequency + ", Notes: " + notes;

        // Create a PatientTreatment object using TreatmentType.THERAPY (assuming it's an enum value)
        PatientTreatment treatment = new PatientTreatment(patient, TreatmentType.THERAPY, description);

        // Save the treatment to the database
        patientTreatmentRepository.save(treatment);
    }
}
