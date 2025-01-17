package com.example.hospital.services.factory;

import java.util.Date;

import com.example.hospital.models.BaseDonation;
import com.example.hospital.models.Donor;

import jakarta.persistence.Entity;

@Entity
public class ChequeDonation extends BaseDonation {

    public ChequeDonation() {
        super();
    }

    public ChequeDonation(Donor donor, float amount, Date date) {
        super(donor, amount, date, "cash");
    }

    @Override
    public String getDonationDetails() {
        return "Cheque donation details: $" + getAmount() + " on " + getDate();
    }

    @Override
    public String generateReceipt() {
        return "Receipt for cheque donation on " + getDate() + "\nAmount: $" + getAmount();
    }

    @Override
    public boolean validateDonation() {
        return getAmount() > 0;
    }
}
