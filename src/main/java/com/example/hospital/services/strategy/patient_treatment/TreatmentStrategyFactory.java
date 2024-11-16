package com.example.hospital.services.strategy.patient_treatment;

import org.springframework.stereotype.Component;

@Component
public class TreatmentStrategyFactory {

    private final MedicationStrategy medicationStrategy;
    private final SurgeryStrategy surgeryStrategy;
    private final TherapyStrategy therapyStrategy;

    public TreatmentStrategyFactory(MedicationStrategy medicationStrategy, SurgeryStrategy surgeryStrategy, TherapyStrategy therapyStrategy) {
        this.medicationStrategy = medicationStrategy;
        this.surgeryStrategy = surgeryStrategy;
        this.therapyStrategy = therapyStrategy;
    }

    public MedicationStrategy getMedicationStrategy() {
        return medicationStrategy;
    }

    public SurgeryStrategy getSurgeryStrategy() {
        return surgeryStrategy;
    }

    public TherapyStrategy getTherapyStrategy() {
        return therapyStrategy;
    }
}
