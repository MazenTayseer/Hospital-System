package com.example.hospital.services.strategy.patient_treatment;

import org.springframework.stereotype.Component;

import com.example.hospital.models.Patient;

@Component
public class TreatmentContext {

    private TreatmentStrategy strategy;

    // Dynamically set the strategy (the treatment type can be passed in the service layer)
    public void setStrategy(TreatmentStrategy strategy) {
        this.strategy = strategy;
    }

    // Apply the treatment using the currently set strategy
    public void applyTreatment(Patient patient, String treatmentDetails) {
        if (this.strategy == null) {
            throw new IllegalStateException("Treatment strategy not set.");
        }
        this.strategy.applyTreatment(patient, treatmentDetails);
    }
}
