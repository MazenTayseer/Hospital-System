package com.example.hospital.dto;

public class AssignPatientDto {
    private Long patientId;

    public AssignPatientDto() {
    }

    public AssignPatientDto(Long patientId) {
        this.patientId = patientId;
    }

    // Getters and Setters
    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }
}
