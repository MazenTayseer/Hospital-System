package com.example.hospital.services.factory;

import java.util.Date;

import com.example.hospital.models.BaseDonation;
import com.example.hospital.models.Donor;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
@DiscriminatorValue("cash")
public class CashDonation extends BaseDonation {

    public CashDonation() {
        super();
    }

    // @Override
    // public String getDonationDetails() {
    //     return "Cheque Donation details: " + donationID + ", Amount: " + amount + ", Date: " + date;
    // }

    // @Override
    // public String generateReceipt() {
    //     return "Cheque Donation receipt for ID " + donationID;
    // }

    // @Override
    // public boolean validateDonation() {
    //     return amount > 0;
    // }

    // @Override
    // public String notifyDonor() {
    //     return "Notification sent for cheque donation.";
    // }

    // Additional getters and setters if needed
}

