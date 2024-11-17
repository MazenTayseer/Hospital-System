package com.example.hospital.models.dto;

public class TherapyTreatmentDTO {
    private Long id;
    private String therapyType;
    private int duration;
    private String frequency;
    private String notes;

    // Constructor
    public TherapyTreatmentDTO(Long id, String therapyType, int duration, String frequency, String notes) {
        this.id = id;
        this.therapyType = therapyType;
        this.duration = duration;
        this.frequency = frequency;
        this.notes = notes;
    }

    // Getters and setters
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
