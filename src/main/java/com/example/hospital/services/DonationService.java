package com.example.hospital.services;

import org.springframework.stereotype.Service;

import com.example.hospital.dal.DonationDAL;
import com.example.hospital.dal.DonorDAL;
import com.example.hospital.dto.DonationDto;
import com.example.hospital.exceptions.BadRequestException;
import com.example.hospital.models.BaseDonation;
import com.example.hospital.models.Donor;
import com.example.hospital.services.factory.DonationFactory;

@Service
public class DonationService {
    private final DonationFactory donationFactory;
    private final DonorDAL donorDAL;
    private final DonationDAL donationDAL;

    public DonationService(DonationFactory donationFactory, DonorDAL donorDAL, DonationDAL donationDAL) {
        this.donationFactory = donationFactory;
        this.donorDAL = donorDAL;
        this.donationDAL = donationDAL;
    }

    public String processDonation(DonationDto request) {

        Donor donor = donorDAL.findById(request.getDonorId());
        if (donor == null) {
            throw new BadRequestException("Invalid donor ID: " + request.getDonorId());
        }

        BaseDonation donation = donationFactory.createDonation(request.getType());
        donation.setAmount(request.getAmount());
        donation.setDate(request.getDate());
        donation.setType(request.getType());
        donation.setDonor(donor);
        
        if (!donation.validateDonation()) {
            throw new BadRequestException("Donation validation failed.");
        }
        
        String receipt = donation.generateReceipt(); 
        donationDAL.save(donation);
        return receipt;
    }
}
