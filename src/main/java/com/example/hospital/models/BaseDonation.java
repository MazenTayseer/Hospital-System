package com.example.hospital.models;

import java.util.Date;

import com.example.hospital.services.factory.IDonation;

import jakarta.persistence.*;

@Entity
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

    public BaseDonation() {}

    public BaseDonation(Donor donor, float amount, Date date, String type) {
        this.donor = donor;
        this.amount = amount;
        this.date = date;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public Donor getDonor() {
        return donor;
    }

    public void setDonor(Donor donor) {
        this.donor = donor;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public abstract String getDonationDetails();

    @Override
    public abstract String generateReceipt();

    @Override
    public abstract boolean validateDonation();
}
