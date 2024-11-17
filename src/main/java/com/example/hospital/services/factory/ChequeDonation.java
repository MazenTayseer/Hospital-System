package com.example.hospital.services.factory;

import java.util.Date;

import com.example.hospital.models.BaseDonation;
import com.example.hospital.models.Donor;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("cheque")
public class ChequeDonation extends BaseDonation {
    private Long donationID;
    private float amount;
    private Date date;
    private Donor donor;

    public ChequeDonation(BaseDonation donation) {
        this.donor = donation.getDonor();
        this.donationID = donation.getId();
        this.amount = donation.getAmount();
        this.date = donation.getDate();
    }

    @Override
    public String getDonationDetails() {
        return "Cheque Donation details: " + donationID + ", Amount: " + amount + ", Date: " + date;
    }

    @Override
    public String generateReceipt() {
        return "Cheque Donation receipt for ID " + donationID;
    }

    @Override
    public boolean validateDonation() {
        return amount > 0;
    }

    @Override
    public String notifyDonor() {
        return "Notification sent for cheque donation.";
    }

    // Additional getters and setters if needed
}

