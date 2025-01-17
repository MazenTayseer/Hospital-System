package com.example.hospital.controllers;


import com.example.hospital.models.Inventory;
import com.example.hospital.services.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
