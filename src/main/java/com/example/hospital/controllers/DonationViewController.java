package com.example.hospital.controllers;

import com.example.hospital.dto.DonationDto;
import com.example.hospital.models.BaseDonation;
import com.example.hospital.services.DonationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.hospital.exceptions.BadRequestException;

import java.util.List;

@Controller
@RequestMapping("/donations")
public class DonationViewController {

    private final DonationService donationService;

    public DonationViewController(DonationService donationService) {
        this.donationService = donationService;
    }

    // Display the form for adding a donation
    @GetMapping("/form")
    public String showDonationForm(Model model) {
        model.addAttribute("donation", new DonationDto());
        return "donation-form";
    }

    // Handle form submission
    @PostMapping("/submit")
    public String submitDonation(@ModelAttribute DonationDto donationDto, Model model) {
        try {
            donationService.processDonation(donationDto);
            return "redirect:/donations/list";
        } catch (BadRequestException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            model.addAttribute("donation", donationDto);
            return "donation-form"; // Re-render the form with error message
        }
    }

    @GetMapping("/list")
    public String listDonations(Model model) {
        List<BaseDonation> donations = donationService.getAllDonations();
        model.addAttribute("donations", donations);
        return "donation-list";
    }
}
