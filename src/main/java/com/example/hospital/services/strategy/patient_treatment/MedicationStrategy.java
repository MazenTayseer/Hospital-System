package com.example.hospital.services.strategy.patient_treatment;

import com.example.hospital.models.Patient;
import com.example.hospital.models.PatientTreatment;
import com.example.hospital.models.enums.TreatmentType;  // Import TreatmentType enum
import com.example.hospital.repositories.PatientTreatmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MedicationStrategy implements TreatmentStrategy {

    @Autowired
    private PatientTreatmentRepository patientTreatmentRepository;

    private String medicationName;
    private String dosage;
    private int duration;
    private String frequency;

    // Setters
    public void setMedicationName(String medicationName) { this.medicationName = medicationName; }
    public void setDosage(String dosage) { this.dosage = dosage; }
    public void setDuration(int duration) { this.duration = duration; }
    public void setFrequency(String frequency) { this.frequency = frequency; }

    @Override
    public void applyTreatment(Patient patient) {
        // Create description string for medication treatment
        String description = "Medication: " + medicationName + ", Dosage: " + dosage +
                             ", Duration: " + duration + " days, Frequency: " + frequency;

        // Create a PatientTreatment object using TreatmentType.MEDICATION (assuming it's an enum value)
        PatientTreatment treatment = new PatientTreatment(patient, TreatmentType.MEDICATION, description);

        // Save the treatment to the database
        patientTreatmentRepository.save(treatment);
    }
}
