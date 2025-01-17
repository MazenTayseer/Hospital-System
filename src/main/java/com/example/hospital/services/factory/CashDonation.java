package com.example.hospital.services.factory;

import com.example.hospital.models.BaseDonation;
import com.example.hospital.models.Donor;

import jakarta.persistence.Entity;
import java.util.Date;

@Entity
public class CashDonation extends BaseDonation {

    public CashDonation() {
        super();
    }

    public CashDonation(Donor donor, float amount, Date date) {
        super(donor, amount, date, "cash");
    }

    @Override
    public String getDonationDetails() {
        return "Cash donation details: $" + getAmount() + " on " + getDate();
    }

    @Override
    public String generateReceipt() {
        return "Receipt for cash donation on " + getDate() + "\nAmount: $" + getAmount();
    }

    @Override
    public boolean validateDonation() {
        return getAmount() > 0;
    }
}
