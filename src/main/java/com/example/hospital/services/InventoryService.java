package com.example.hospital.services;

import com.example.hospital.models.Inventory;
import com.example.hospital.repositories.InventoryRepository;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

  private final InventoryRepository inventoryRepository;

  public InventoryService(InventoryRepository inventoryRepository) {
    this.inventoryRepository = inventoryRepository;
  }

  public boolean isMedicationAvailable(String medicationName, int quantity) {
    // Fetch the inventory record for the given medication
    Inventory inventory = inventoryRepository.findByMedicationName(medicationName);

    // Check if the medication is available in the required quantity
    if (inventory != null && inventory.getQuantity() >= quantity) {
      return true; // Medication is available
    }

    return false; // Medication is unavailable or insufficient stock
  }

  public void updateStock(String medicationName, int quantity) {
    Inventory inventory = inventoryRepository.findByMedicationName(medicationName);

    if (inventory == null) {
      throw new IllegalArgumentException("Medication not found: " + medicationName);
    }
    if (inventory == null) {
      throw new IllegalArgumentException("Medication not found: " + medicationName);
    }

    if (inventory.getQuantity() < quantity) {
      throw new IllegalArgumentException("Insufficient stock for medication: " + medicationName);
    }
    if (inventory.getQuantity() < quantity) {
      throw new IllegalArgumentException("Insufficient stock for medication: " + medicationName);
    }

    inventory.setQuantity(inventory.getQuantity() - quantity);
    inventoryRepository.save(inventory);
  }

  public Inventory addMedication(String medicationName, double unitPrice, int quantity) {
    Inventory inventory = inventoryRepository.findByMedicationName(medicationName);

    if (inventory == null) {
      // Create a new inventory entry if it doesn't exist
      inventory = new Inventory();
      inventory.setMedicationName(medicationName);
      inventory.setUnitPrice(unitPrice);
      inventory.setQuantity(quantity);
    } else {
      // Update the existing inventory
      inventory.setQuantity(inventory.getQuantity() + quantity);
      inventory.setUnitPrice(unitPrice); // Update unit price if needed
    }

    return inventoryRepository.save(inventory);
  }

  public Iterable<Inventory> getAllInventory() {
    return inventoryRepository.findAll();
  }

  public Inventory findByMedicationName(String medicationName) {
    return inventoryRepository.findByMedicationName(medicationName);
  }

  public void addOrUpdateInventory(String medicationName, int quantity) {
    Inventory inventory = inventoryRepository.findByMedicationName(medicationName);
    if (inventory == null) {
      throw new IllegalArgumentException("Medication not recognized: " + medicationName);
    }
    inventory.setQuantity(inventory.getQuantity() + quantity);
    inventoryRepository.save(inventory);
  }
}
