package com.example.hospital.models.dto;

import com.example.hospital.models.enums.TreatmentType;
import java.time.LocalDate;

public class PatientTreatmentDTO {

    private Long id;
    private TreatmentType treatmentType;
    private String description;
    private LocalDate date;

    // Constructors, Getters and Setters
    public PatientTreatmentDTO(Long id, TreatmentType treatmentType, String description, LocalDate date) {
        this.id = id;
        this.treatmentType = treatmentType;
        this.description = description;
        this.date = date;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TreatmentType getTreatmentType() {
        return treatmentType;
    }

    public void setTreatmentType(TreatmentType treatmentType) {
        this.treatmentType = treatmentType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
