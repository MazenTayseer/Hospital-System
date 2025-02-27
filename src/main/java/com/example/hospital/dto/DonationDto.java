package com.example.hospital.dto;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class DonationDto {
    private String type;
    private float amount;
    private LocalDate date; // Use LocalDate instead of Date
    private Long donorId;
    private List<MedicineItemDto> medicineItems; // Applicable for medicine donations

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getDonorId() {
        return donorId;
    }

    public void setDonorId(Long donorId) {
        this.donorId = donorId;
    }

    public List<MedicineItemDto> getMedicineItems() {
        return medicineItems;
    }

    public void setMedicineItems(List<MedicineItemDto> medicineItems) {
        this.medicineItems = medicineItems;
    }
}
