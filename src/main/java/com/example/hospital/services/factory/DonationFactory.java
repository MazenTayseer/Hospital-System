package com.example.hospital.services.factory;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.example.hospital.models.BaseDonation;
import com.example.hospital.models.Donor;
@Component
public class DonationFactory {
    public static IDonation createDonation(BaseDonation donation) {
        switch (donation.getType()) {
            case "online":
                return new OnlineDonation(donation);
            case "cheque":
                return new ChequeDonation(donation);
            case "cash":
                return new CashDonation(donation);
            default:
                throw new IllegalArgumentException("Invalid donation type");
        }
    }
}
