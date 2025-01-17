package com.example.hospital.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hospital.dal.DonationDAL;
import com.example.hospital.dal.DonorDAL;
import com.example.hospital.dto.DonationDto;
import com.example.hospital.dto.UserDto;
import com.example.hospital.exceptions.BadRequestException;
import com.example.hospital.models.BaseDonation;
import com.example.hospital.models.Donor;
import com.example.hospital.models.User;
import com.example.hospital.services.strategy.create_user.CreateUserContext;
import com.example.hospital.services.factory.DonationFactory;
import com.example.hospital.models.Inventory;
import com.example.hospital.services.adapter.MedicineDonationAdapter;
import com.example.hospital.models.MedicineItem;

@Service
public class DonationService {
    private final DonationFactory donationFactory;
    private final DonorDAL donorDAL;
    private final DonationDAL donationDAL;
    private final CreateUserContext createUserContext;
    private final InventoryService inventoryService;

    @Autowired
    public DonationService(DonationFactory donationFactory, DonorDAL donorDAL, DonationDAL donationDAL,
            CreateUserContext createUserContext, InventoryService inventoryService) {
        this.donationFactory = donationFactory;
        this.donorDAL = donorDAL;
        this.donationDAL = donationDAL;
        this.createUserContext = createUserContext;
        this.inventoryService = inventoryService;
    }

    public User createUser(UserDto<? extends User> request) {
        return createUserContext.createUser(request);
    }

    public String processDonation(DonationDto request) {

        Donor donor = donorDAL.findById(request.getDonorId());
        if (donor == null) {
            throw new BadRequestException("Invalid donor ID: " + request.getDonorId());
        }

        BaseDonation donation = donationFactory.createDonation(request.getType(), request, donor);

        // If it's a medicine donation, handle inventory updates
        if (donation instanceof MedicineDonationAdapter) {
            MedicineDonationAdapter medicineDonation = (MedicineDonationAdapter) donation;

            // Calculate the total amount based on inventory prices
            float totalAmount = 0;
            for (MedicineItem item : medicineDonation.getMedicineDonation().getMedicineItems()) {
                Inventory inventory = inventoryService.findByMedicationName(item.getMedicationName());
                if (inventory == null) {
                    throw new BadRequestException("Medication not found: " + item.getMedicationName());
                }
                totalAmount += inventory.getUnitPrice() * item.getQuantity();
            }
            // Set the calculated amount
            donation.setAmount(totalAmount);

            // Update the inventory by adding the donated quantities
            for (MedicineItem item : medicineDonation.getMedicineDonation().getMedicineItems()) {
                inventoryService.addOrUpdateInventory(item.getMedicationName(), item.getQuantity());
            }
        }

        // donation.setAmount(request.getAmount());
        // donation.setDate(request.getDate());
        // donation.setType(request.getType());
        // donation.setDonor(donor);

        if (!donation.validateDonation()) {
            throw new BadRequestException("Donation validation failed.");
        }

        String receipt = donation.generateReceipt();
        donationDAL.save(donation);
        return receipt;
    }
}
