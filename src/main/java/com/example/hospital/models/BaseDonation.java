package com.example.hospital.models;

import java.util.Date;

import com.example.hospital.services.factory.IDonation;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.DiscriminatorType;

@Entity
@Data
@Table(name = "donations")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "donation_type", discriminatorType = DiscriminatorType.STRING)
public class BaseDonation implements IDonation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "donor_id", nullable = false)
    private Donor donor;
    
    private float amount;
    private Date date;
    private String type; 
    public BaseDonation (){
        
    }

    // Getters and Setters

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
    
    public Donor getDonor() {
        return donor;
    }

    public void setDonor(Donor donor) {
        this.donor = donor;
    }

    @Override
    public String getDonationDetails() {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'getDonationDetails'");
        return "yess";
    }

    @Override
    public String generateReceipt() {
        // TODO Auto-generated method stub
        return "yess";
        //throw new UnsupportedOperationException("Unimplemented method 'generateReceipt'");
    }

    @Override
    public boolean validateDonation() {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'validateDonation'");
        return true;
    }

    @Override
    public String notifyDonor() {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'notifyDonor'");
        return "yess";
    }
}

