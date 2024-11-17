package com.example.hospital.models;

import java.util.Date;

import com.example.hospital.services.factory.IDonation;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class BaseDonation implements IDonation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "donor_id", nullable = false)
    private Donor donor;
    
    @Column(nullable = false)
    private float amount;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private String type; 

    public BaseDonation (){}

    public BaseDonation(Donor donor, float amount, Date date, String type) {
        this.donor = donor;
        this.amount = amount;
        this.date = date;
        this.type = type;
    }

    @Override
    public abstract String getDonationDetails();

    @Override
    public abstract String generateReceipt();

    @Override
    public abstract boolean validateDonation();
}
