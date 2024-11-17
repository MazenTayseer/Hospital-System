package com.example.hospital.models.dto;

public class SurgeryTreatmentDTO {
    private Long id;
    private String surgeryType;
    private String location;
    private String surgeon;
    private String date;

    // Constructor
    public SurgeryTreatmentDTO(Long id, String surgeryType, String location, String surgeon, String date) {
        this.id = id;
        this.surgeryType = surgeryType;
        this.location = location;
        this.surgeon = surgeon;
        this.date = date;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
