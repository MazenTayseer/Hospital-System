package com.example.hospital.services.strategy.patient_treatment;

import com.example.hospital.models.Patient;
import org.springframework.stereotype.Component;

@Component
public class TreatmentContext {

    private TreatmentStrategy strategy;

    // Method to set the treatment strategy at runtime
    public void setStrategy(TreatmentStrategy strategy) {
      this.strategy = strategy;
    }

    public TreatmentStrategy getStrategy() {
      return strategy;
  }

    // Method to apply the treatment
    public void applyTreatment(Patient patient) {
        if (strategy == null) {
            throw new IllegalStateException("Treatment strategy not set");
        }
        strategy.applyTreatment(patient);
    }
}
