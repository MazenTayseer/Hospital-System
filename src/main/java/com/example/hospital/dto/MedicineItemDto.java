package com.example.hospital.dto;

public class MedicineItemDto {
    private String medicationName;
    private int quantity;

    public MedicineItemDto() {
    }

    public MedicineItemDto(String medicationName, int quantity) {
        this.medicationName = medicationName;
        this.quantity = quantity;
    }

    // Getters and Setters
    public String getMedicationName() {
        return medicationName;
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
