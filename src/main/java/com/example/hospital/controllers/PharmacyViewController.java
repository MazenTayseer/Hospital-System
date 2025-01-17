package com.example.hospital.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.hospital.services.facade.PharmacyFacade;


@Controller
@RequestMapping("/pharmacy")
public class PharmacyViewController {

    @Autowired
    private final PharmacyFacade pharmacyFacade;

    public PharmacyViewController(PharmacyFacade pharmacyFacade) {
        this.pharmacyFacade = pharmacyFacade;
    }

    @GetMapping("/dispense-form")
    public ModelAndView renderDispenseForm() {
        ModelAndView mav = new ModelAndView("pharmacy-dispense-form");
        mav.addObject("pageTitle", "Dispense Medication");
        return mav;
    }

    @PostMapping("/dispense")
    public ModelAndView dispenseMedication(
            @RequestParam Long patientId,
            @RequestParam Long doctorId,
            @RequestParam String medicationName,
            @RequestParam int quantity) {
        ModelAndView mav = new ModelAndView("pharmacy-dispense-result");
        try {
            pharmacyFacade.dispenseMedication(patientId, doctorId, medicationName, quantity);
            mav.addObject("message", "Medication dispensed successfully.");
        } catch (IllegalArgumentException e) {
            mav.addObject("error", e.getMessage());
        }
        return mav;
    }
}
