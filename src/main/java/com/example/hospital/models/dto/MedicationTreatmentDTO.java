package com.example.hospital.models.dto;

public class MedicationTreatmentDTO {
    private Long id;
    private String medicationName;
    private String dosage;
    private int duration;
    private String frequency;

    // Constructor
    public MedicationTreatmentDTO(Long id, String medicationName, String dosage, int duration, String frequency) {
        this.id = id;
        this.medicationName = medicationName;
        this.dosage = dosage;
        this.duration = duration;
        this.frequency = frequency;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMedicationName() {
        return medicationName;
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }
}
