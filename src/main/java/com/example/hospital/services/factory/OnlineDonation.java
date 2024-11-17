package com.example.hospital.services.factory;

import com.example.hospital.models.BaseDonation;

import jakarta.persistence.Entity;

@Entity
public class OnlineDonation extends BaseDonation {
    @Override
    public String getDonationDetails() {
        return "Online donation details";
    }

    @Override
    public String generateReceipt() {
        return "Receipt for Online donation";
    }

    @Override
    public boolean validateDonation() {
        return getAmount() > 0;
    }
}

