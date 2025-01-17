package com.example.hospital.services.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hospital.models.Doctor;
import com.example.hospital.models.Patient;
import com.example.hospital.services.BillingService;
import com.example.hospital.services.DoctorService;
import com.example.hospital.services.InventoryService;
import com.example.hospital.services.PatientService;
import com.example.hospital.services.PrescriptionService;


@Service
public class PharmacyFacade {

    @Autowired
    private final PatientService patientService;

    @Autowired
    private final DoctorService doctorService;

    @Autowired
    private final InventoryService inventoryService;

    @Autowired
    private final BillingService billingService;

    @Autowired
    private final PrescriptionService prescriptionService;

    public PharmacyFacade(PatientService patientService, DoctorService doctorService,
                          InventoryService inventoryService, BillingService billingService,
                          PrescriptionService prescriptionService) {
        this.patientService = patientService;
        this.doctorService = doctorService;
        this.inventoryService = inventoryService;
        this.billingService = billingService;
        this.prescriptionService = prescriptionService;
    }

    public void dispenseMedication(Long patientId, Long doctorId, String medicationName, int quantity) {

        // Retrieve patient details
        Patient patient = patientService.getPatientById(patientId);
        if (patient == null) {
          throw new IllegalArgumentException("Invalid patient ID: " + patientId);
        }

        // Retrieve doctor details
        Doctor doctor = doctorService.getById(doctorId);
        if (doctor == null) {
          throw new IllegalArgumentException("Doctor not found for ID: " + doctorId);
        }

        if (!prescriptionService.validatePrescription(patientId, doctorId, medicationName)) {
            throw new IllegalArgumentException("Invalid prescription");
        }

        if (!inventoryService.isMedicationAvailable(medicationName, quantity)) {
            throw new IllegalArgumentException("Insufficient stock for " + medicationName);
        }

        // Deduct stock and generate bill
        inventoryService.updateStock(medicationName, quantity);

        double billAmount = billingService.calculateBill(medicationName, quantity);
        billingService.generateBill(patientId, billAmount);

    }
}
