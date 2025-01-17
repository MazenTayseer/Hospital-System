package com.example.hospital.services;

import java.rmi.RemoteException;

import org.springframework.stereotype.Service;

import com.example.hospital.dal.DonationDAL;
import com.example.hospital.dal.DonorDAL;
import com.example.hospital.dto.DonationDto;
import com.example.hospital.exceptions.BadRequestException;
import com.example.hospital.models.BaseDonation;
import com.example.hospital.models.Donor;
import com.example.hospital.services.factory.DonationFactory;
import com.example.hospital.services.proxy.remote.PaymentProxy;

@Service
public class DonationService {
    private final DonationFactory donationFactory;
    private final DonorDAL donorDAL;
    private final DonationDAL donationDAL;
    private final PaymentProxy paymentProxy;

    public DonationService(DonationFactory donationFactory, DonorDAL donorDAL, DonationDAL donationDAL, PaymentProxy paymentProxy) {
        this.donationFactory = donationFactory;
        this.donorDAL = donorDAL;
        this.donationDAL = donationDAL;
        this.paymentProxy = paymentProxy;
    }

    public String processDonation(DonationDto request) throws RemoteException {

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
        
        String paymentResponse = paymentProxy.processPayment(request.getDonorId(), request.getAmount());
        System.out.println("Payment response: " + paymentResponse);
        String receipt = donation.generateReceipt(); 
        donationDAL.save(donation);
        return receipt;
    }
}
