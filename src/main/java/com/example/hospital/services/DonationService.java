package com.example.hospital.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hospital.dal.DonationDAL;
import com.example.hospital.dal.DonorDAL;
import com.example.hospital.exceptions.BadRequestException;
import com.example.hospital.models.BaseDonation;
import com.example.hospital.models.Donor;
import com.example.hospital.models.User;
import com.example.hospital.services.factory.IDonation;
import com.example.hospital.services.strategy.create_user.CreateUserContext;
import com.example.hospital.services.factory.DonationFactory;

@Service
public class DonationService {
    private final DonationFactory donationFactory;
    private final DonorDAL donorDAL;
    private final DonationDAL donationDAL;
    private final CreateUserContext createUserContext;
    // private final NotificationService notificationService;

    @Autowired
    public DonationService(DonationFactory donationFactory , DonorDAL donorDAL, DonationDAL donationDAL , CreateUserContext createUserContext/*, NotificationService notificationService */) {
        this.donationFactory = donationFactory;
        this.donorDAL = donorDAL;
        this.donationDAL = donationDAL;
        this.createUserContext = createUserContext;
      //  this.notificationService = notificationService;
    }

    public User createUser(User user) {
    return createUserContext.createUser(user);
    }

    public String processDonation(BaseDonation newDonation) {

        Donor donor = donorDAL.findById(newDonation.getDonor().getId());
        if (donor == null) {
            throw new BadRequestException("Invalid donor ID: " + newDonation.getDonor().getId());
        }

        BaseDonation donation = (BaseDonation) DonationFactory.createDonation(newDonation);

        donation.setDonor(donor);
        
        if (donation.validateDonation()) {
            // Process donation logic (e.g., save to database)


            String receipt = donation.generateReceipt();
            System.out.println(receipt);
                    // Process and save the donation
            
            
            donor.addDonation(donation);
            donationDAL.save(donation);
            //String notificationMessage = donation.notifyDonor();
            //notificationService.sendNotification(donor.getEmail(), notificationMessage);
            return receipt;


        } else {
            return "Donation validation failed.";
        }
    }
}

