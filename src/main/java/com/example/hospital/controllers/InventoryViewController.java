package com.example.hospital.controllers;

import com.example.hospital.models.Inventory;
import com.example.hospital.services.InventoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class InventoryViewController {

    private final InventoryService inventoryService;

    public InventoryViewController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    // GET: Render the form for adding medication
    @GetMapping("/inventory/add")
    public String showAddInventoryForm(Model model) {
        model.addAttribute("inventory", new Inventory());
        return "inventory-add-form"; // Points to inventory-add-form.html
    }

    // POST: Handle form submission to add medication
    @PostMapping("/inventory/add")
    public String addInventory(@ModelAttribute Inventory inventory, Model model) {
        inventoryService.addMedication(
                inventory.getMedicationName(),
                inventory.getUnitPrice(),
                inventory.getQuantity()
        );
        model.addAttribute("message", "Medication added successfully!");
        return "redirect:/inventory/view";
    }

    // GET: View all inventory
    @GetMapping("/inventory/view")
    public String viewAllInventory(Model model) {
        model.addAttribute("inventoryList", inventoryService.getAllInventory());
        return "inventory-view"; // Points to inventory-view.html
    }
}
