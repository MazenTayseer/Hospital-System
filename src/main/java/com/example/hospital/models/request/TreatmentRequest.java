package com.example.hospital.models.request;

import com.example.hospital.models.enums.TreatmentType;

import jakarta.validation.constraints.NotNull;

public class TreatmentRequest {

   @NotNull
    private Long patientId;

    @NotNull
    private TreatmentType treatmentType;

    // Attributes for Medical treatment
    private String medicationName;
    private String dosage;
    private int duration;
    private String frequency;

    // Attributes for Surgery treatment
    private String surgeryType;
    private String location;
    private String surgeon;
    private String date;

    // Attributes for Therapy treatment
    private String therapyType;
    private int therapyDuration;
    private String therapyFrequency;
    private String therapyNotes;

    // Getters and Setters

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public TreatmentType getTreatmentType() {
        return treatmentType;
    }

    public void setTreatmentType(TreatmentType treatmentType) {
        this.treatmentType = treatmentType;
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

    public String getSurgeryType() {
        return surgeryType;
    }

    public void setSurgeryType(String surgeryType) {
        this.surgeryType = surgeryType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSurgeon() {
        return surgeon;
    }

    public void setSurgeon(String surgeon) {
        this.surgeon = surgeon;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTherapyType() {
        return therapyType;
    }

    public void setTherapyType(String therapyType) {
        this.therapyType = therapyType;
    }

    public int getTherapyDuration() {
        return therapyDuration;
    }

    public void setTherapyDuration(int therapyDuration) {
        this.therapyDuration = therapyDuration;
    }

    public String getTherapyFrequency() {
        return therapyFrequency;
    }

    public void setTherapyFrequency(String therapyFrequency) {
        this.therapyFrequency = therapyFrequency;
    }

    public String getTherapyNotes() {
        return therapyNotes;
    }

    public void setTherapyNotes(String therapyNotes) {
        this.therapyNotes = therapyNotes;
    }
}
