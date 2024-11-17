package com.example.hospital.services.factory;


import org.springframework.stereotype.Component;

import com.example.hospital.models.BaseDonation;

@Component
public class DonationFactory {
    public BaseDonation createDonation(String type) {
        return switch (type) {
            case "cash" -> new CashDonation();
            case "cheque" -> new ChequeDonation();
            case "online" -> new OnlineDonation();
            default -> throw new IllegalArgumentException("Invalid donation type: " + type);
        };
    }
}
