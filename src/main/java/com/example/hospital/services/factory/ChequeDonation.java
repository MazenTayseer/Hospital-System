package com.example.hospital.services.factory;

import com.example.hospital.models.BaseDonation;

import jakarta.persistence.Entity;

@Entity
public class ChequeDonation extends BaseDonation {
    @Override
    public String getDonationDetails() {
        return "Cheque donation details";
    }

    @Override
    public String generateReceipt() {
        return "Receipt for cheque donation";
    }

    @Override
    public boolean validateDonation() {
        return getAmount() > 0;
    }
}

