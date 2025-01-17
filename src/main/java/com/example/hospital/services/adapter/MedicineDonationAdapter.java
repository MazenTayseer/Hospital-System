package com.example.hospital.services.adapter;

import com.example.hospital.models.BaseDonation;
import com.example.hospital.models.Donor;
import com.example.hospital.models.MedicineDonation;
import com.example.hospital.models.MedicineItem;
import com.example.hospital.services.InventoryService;

import jakarta.persistence.*;

@Entity
public class MedicineDonationAdapter extends BaseDonation {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "medicine_donation_id", referencedColumnName = "id")
    private MedicineDonation medicineDonation;

    @Transient
    private InventoryService inventoryService;

    public MedicineDonationAdapter() {
        super();
    }

    public MedicineDonationAdapter(MedicineDonation medicineDonation, Donor donor, InventoryService inventoryService) {
        super(donor, 0, medicineDonation.getDonationDate(), "medicine");
        this.medicineDonation = medicineDonation;
        this.inventoryService = inventoryService;
    }

    // Getters and Setters
    public MedicineDonation getMedicineDonation() {
        return medicineDonation;
    }

    public void setMedicineDonation(MedicineDonation medicineDonation) {
        this.medicineDonation = medicineDonation;
    }

    @Override
    public String getDonationDetails() {
        StringBuilder details = new StringBuilder("Medicine Donation Details:\n");
        for (MedicineItem item : medicineDonation.getMedicineItems()) {
            details.append("Medication: ").append(item.getMedicationName())
                    .append(", Quantity: ").append(item.getQuantity()).append("\n");
        }
        return details.toString();
    }

    @Override
    public String generateReceipt() {
        return "Receipt for Medicine Donation on " + getDate() + "\n" + getDonationDetails();
    }

    @Override
    public boolean validateDonation() {
        for (MedicineItem item : medicineDonation.getMedicineItems()) {
            if (item.getQuantity() <= 0 || item.getMedicationName() == null || item.getMedicationName().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void setAmount(float amount) {
        // Amount is calculated based on inventory prices
        super.setAmount(amount);
    }

    @Override
    public float getAmount() {
        return super.getAmount();
    }
}
