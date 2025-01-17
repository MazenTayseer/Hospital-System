package com.example.hospital.controllers;

import com.example.hospital.models.Prescription;
import com.example.hospital.services.PrescriptionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/prescriptions")
public class PrescriptionViewController {

    private final PrescriptionService prescriptionService;

    public PrescriptionViewController(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    @GetMapping
    public String listPrescriptions(Model model) {
        model.addAttribute("prescriptions", prescriptionService.getAllPrescriptions());
        return "prescription-view"; // Use the file name directly, no subfolder required
    }

    @GetMapping("/new")
    public String newPrescriptionForm(Model model) {
        model.addAttribute("prescription", new Prescription());
        return "prescription-add-form"; // Use the file name directly, no subfolder required
    }

    @PostMapping
    public String savePrescription(@RequestParam Long patientId,
                                   @RequestParam Long doctorId,
                                   @RequestParam String medicationName,
                                   @RequestParam int quantity,
                                   @RequestParam String issueDate,
                                   @RequestParam String expiryDate) {

        // Call the service to add the prescription using request parameters
        prescriptionService.addPrescription(patientId, doctorId, medicationName, quantity, issueDate, expiryDate);

        return "redirect:/prescriptions"; // Redirect to the list of prescriptions after saving
    }
}
