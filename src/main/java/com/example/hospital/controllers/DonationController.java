package com.example.hospital.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hospital.services.DonationService;
import com.example.hospital.dto.DonationDto;
import com.example.hospital.models.Donor;

@RestController
@RequestMapping("/api/donations")
public class DonationController {
    private final DonationService donationService;

    public DonationController(DonationService donationService) {
        this.donationService = donationService;
    }

    @PostMapping("/create-donor")
    public ResponseEntity<Donor> createDonor(@RequestBody Donor donor) {
        Donor createdUser = (Donor) donationService.createUser(donor);
        return ResponseEntity.ok(createdUser);
    }
    @PostMapping("/create")
    public ResponseEntity<String> createDonation(
        @RequestBody DonationDto request) {
        String receipt = donationService.processDonation(request);
        return ResponseEntity.ok(receipt);
    }
}
