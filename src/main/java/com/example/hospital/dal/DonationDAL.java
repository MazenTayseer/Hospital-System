package com.example.hospital.dal;

import org.springframework.stereotype.Component;
import com.example.hospital.ResponseMessages;
import com.example.hospital.exceptions.BadRequestException;
import com.example.hospital.models.BaseDonation;
import com.example.hospital.repositories.DonationRepository;

@Component
public class DonationDAL {
    private final DonationRepository donationRepository;

    public DonationDAL(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    public BaseDonation findById(Long id) {
        return donationRepository.findById(id).orElseThrow(
            () -> new BadRequestException(ResponseMessages.record_not_found("Donation"))
        );
    }

    public BaseDonation save(BaseDonation donation) {
        return donationRepository.save(donation);
    }

    
    public void deleteAll() {
        donationRepository.deleteAll();
    }

    
    public void delete(BaseDonation donation) {
        donationRepository.delete(donation);
    }
}



