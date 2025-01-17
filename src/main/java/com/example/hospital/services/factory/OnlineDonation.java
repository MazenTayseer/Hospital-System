package com.example.hospital.services.factory;

import java.time.LocalDate;

import com.example.hospital.models.BaseDonation;
import com.example.hospital.models.Donor;

import jakarta.persistence.Entity;

@Entity
public class OnlineDonation extends BaseDonation {
    public OnlineDonation() {
        super();
    }

    public OnlineDonation(Donor donor, float amount, LocalDate date) {
        super(donor, amount, date, "online");
    }

    @Override
    public String getDonationDetails() {
        return "Online donation details: $" + getAmount() + " on " + getDate();
    }

    @Override
    public String generateReceipt() {
        return "Receipt for Online donation on " + getDate() + "\nAmount: $" + getAmount();
    }

    @Override
    public boolean validateDonation() {
        return getAmount() > 0;
    }
}
