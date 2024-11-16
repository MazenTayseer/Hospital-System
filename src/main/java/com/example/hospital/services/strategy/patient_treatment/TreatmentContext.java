package com.example.hospital.services.strategy.patient_treatment;

import com.example.hospital.models.Patient;
import org.springframework.stereotype.Component;

@Component
public class TreatmentContext {

    private TreatmentStrategy strategy;

    // Set the strategy dynamically
    public void setStrategy(TreatmentStrategy strategy) {
        this.strategy = strategy;
    }

    // Apply the strategy to assign treatment
    public void applyTreatment(Patient patient, Object... args) {
        if (strategy == null) {
            throw new IllegalStateException("Treatment strategy not set");
        }
        strategy.applyTreatment(patient, args);
    }
}
