package com.example.hospital.services.factory;

public interface IDonation {
    String getDonationDetails();
    String generateReceipt();
    boolean validateDonation();
}
