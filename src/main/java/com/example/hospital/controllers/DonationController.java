package com.example.hospital.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hospital.services.DonationService;
import com.example.hospital.dto.DonationDto;
import com.example.hospital.dto.UserDto;
import com.example.hospital.models.BaseDonation;
import com.example.hospital.models.Donor;

@RestController
@RequestMapping("/api/donations")
public class DonationController {
    private final DonationService donationService;

    public DonationController(DonationService donationService) {
        this.donationService = donationService;
    }

    @PostMapping("/create-donor")
    public ResponseEntity<Donor> createDonor(@RequestBody UserDto<Donor> request) {
        Donor createdUser = (Donor) donationService.createUser(request);
        return ResponseEntity.ok(createdUser);
    }
    @PostMapping("/create")
    public ResponseEntity<String> createDonation(
        @RequestBody DonationDto request) {
      String receipt = donationService.processDonation(request);
      return ResponseEntity.ok(receipt);
    }

    @GetMapping("/all")
    public ResponseEntity<List<BaseDonation>> getAllDonations() {
        List<BaseDonation> donations = donationService.getAllDonations();
        return ResponseEntity.ok(donations);
    }
}
