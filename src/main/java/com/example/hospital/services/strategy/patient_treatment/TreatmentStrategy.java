package com.example.hospital.services.strategy.patient_treatment;

import com.example.hospital.models.Patient;

public interface TreatmentStrategy {
    void applyTreatment(Patient patient);
}
