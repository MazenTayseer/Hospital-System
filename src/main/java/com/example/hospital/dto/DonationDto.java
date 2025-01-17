package com.example.hospital.dto;

import java.util.Date;
import java.util.List;

public class DonationDto {
    private String type;
    private float amount;
    private Date date;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
