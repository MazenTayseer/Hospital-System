package com.example.hospital.dto;

public class TherapyTreatmentDto {

    private Long id;
    private String therapyType;
    private int therapyDuration;
    private String therapyFrequency;
    private String therapyNotes;

    // Constructor
    public TherapyTreatmentDto(Long id, String therapyType, int therapyDuration, String therapyFrequency, String therapyNotes) {
        this.id = id;
        this.therapyType = therapyType;
        this.therapyDuration = therapyDuration;
        this.therapyFrequency = therapyFrequency;
        this.therapyNotes = therapyNotes;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
