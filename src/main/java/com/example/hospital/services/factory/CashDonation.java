package com.example.hospital.services.factory;

import com.example.hospital.models.BaseDonation;

import jakarta.persistence.Entity;

@Entity
public class CashDonation extends BaseDonation {
    @Override
    public String getDonationDetails() {
        return "Cash donation details";
    }

    @Override
    public String generateReceipt() {
        return "Receipt for cash donation";
    }

    @Override
    public boolean validateDonation() {
        return getAmount() > 0;
    }
}
