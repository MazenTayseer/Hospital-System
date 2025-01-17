package com.example.hospital.models;

import jakarta.persistence.Embeddable;

@Embeddable
public class MedicineItem {
    private String medicationName;
    private int quantity;

    public MedicineItem() {
    }

    public MedicineItem(String medicationName, int quantity) {
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
