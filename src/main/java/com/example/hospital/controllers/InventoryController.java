package com.example.hospital.controllers;

import com.example.hospital.models.Inventory;
import com.example.hospital.services.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.hospital.exceptions.BadRequestException;

@RestController
@RequestMapping("/api/pharmacy/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    // GET: Fetch all inventory items
    @GetMapping
    public ResponseEntity<Iterable<Inventory>> getAllInventory() {
        Iterable<Inventory> inventoryList = inventoryService.getAllInventory();
        return ResponseEntity.ok(inventoryList);
    }

    // POST: Add a new medication or update existing medication in inventory
    @PostMapping("/add")
    public ResponseEntity<Inventory> addMedication(
            @RequestParam String medicationName,
            @RequestParam double unitPrice,
            @RequestParam int quantity) {
        try {
            Inventory inventory = inventoryService.addMedication(medicationName, unitPrice, quantity);
            return ResponseEntity.ok(inventory);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // NEW: GET: Fetch unit price for a specific medication
    @GetMapping("/unit-price")
    public ResponseEntity<Double> getUnitPrice(@RequestParam String medicationName) {
        if (medicationName == null || medicationName.trim().isEmpty()) {
            throw new BadRequestException("Medication name is required.");
        }

        Inventory inventory = inventoryService.findByMedicationName(medicationName.trim());
        if (inventory == null) {
            throw new BadRequestException("Medication not found: " + medicationName);
        }

        return ResponseEntity.ok(inventory.getUnitPrice());
    }
}
