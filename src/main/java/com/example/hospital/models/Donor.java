package com.example.hospital.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.hospital.models.enums.Gender;
import com.example.hospital.services.factory.IDonation;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "donors")
public class Donor extends User {
    

    @OneToMany(mappedBy = "donor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BaseDonation> donationHistory;

    public Donor() {}

    public Donor(
        String firstName,
        String lastName,
        String email,
        String password,
        String phone,
        int age,
        Gender gender
    ) {
        super(firstName, lastName, email, password, phone, age, gender);
        donationHistory = new ArrayList<>();
    }

    // Getters and Setters


    public void addDonation(BaseDonation donation) {
        donationHistory.add(donation);
        donation.setDonor(this); // Ensure bidirectional consistency
    }

    public List<BaseDonation> getDonationHistory() { return donationHistory; }
    public void setDonationHistory(List<BaseDonation> donationHistory) { this.donationHistory = donationHistory; }

    
}



