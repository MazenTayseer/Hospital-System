package com.example.hospital.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.hospital.models.Bill;
import com.example.hospital.models.Inventory;
import com.example.hospital.models.Patient;
import com.example.hospital.repositories.BillRepository;
import com.example.hospital.repositories.InventoryRepository;

@Service
public class BillingService {

  private final InventoryRepository inventoryRepository;
      private final BillRepository billRepository;
    private final PatientService patientService;

    public BillingService(InventoryRepository inventoryRepository,BillRepository billRepository, PatientService patientService) {
      this.inventoryRepository = inventoryRepository;
      this.billRepository = billRepository;
      this.patientService = patientService;
    }

    public double calculateBill(String medicationName, int quantity) {
      Inventory inventory = inventoryRepository.findByMedicationName(medicationName);

      if (inventory == null) {
        throw new IllegalArgumentException("Medication not found: " + medicationName);
      }

      return inventory.getUnitPrice() * quantity;
    }

    public void generateBill(Long patientId, double amount) {
      Patient patient = patientService.getPatientById(patientId);

      if (patient == null) {
        throw new IllegalArgumentException("Patient not found with ID: " + patientId);
      }

      Bill bill = new Bill(patient, amount, LocalDate.now());
      billRepository.save(bill);
    }

    public List<Bill> getAllBills() {
      return billRepository.findAll(); // Fetch all the bills from the database
  }
}
