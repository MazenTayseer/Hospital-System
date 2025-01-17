package com.example.hospital.controllers;

import com.example.hospital.services.BillingService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bills")
public class BillViewController {

    private final BillingService billService;

    public BillViewController(BillingService billService) {
        this.billService = billService;
    }

    @GetMapping
    public String listBills(Model model) {
        model.addAttribute("bills", billService.getAllBills());
        return "bill-view"; // Render the bill-view template
    }
}
