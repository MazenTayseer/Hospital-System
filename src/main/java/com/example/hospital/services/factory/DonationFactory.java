package com.example.hospital.services.factory;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.hospital.dto.DonationDto;
import com.example.hospital.models.BaseDonation;
import com.example.hospital.models.Donor;
import com.example.hospital.models.MedicineDonation;
import com.example.hospital.models.MedicineItem;
import com.example.hospital.services.InventoryService;
import com.example.hospital.services.adapter.MedicineDonationAdapter;
import java.util.List;

@Component
public class DonationFactory {
    @Autowired
    private InventoryService inventoryService;

    public BaseDonation createDonation(String type, DonationDto dto, Donor donor) {
        switch (type.toLowerCase()) {
            case "cash":
                return new CashDonation(donor, dto.getAmount(), dto.getDate());
            case "cheque":
                return new ChequeDonation(donor, dto.getAmount(), dto.getDate());
            case "online":
                return new OnlineDonation(donor, dto.getAmount(), dto.getDate());
            case "medicine":
                List<MedicineItem> items = dto.getMedicineItems().stream()
                        .map(mi -> new MedicineItem(mi.getMedicationName(), mi.getQuantity()))
                        .collect(Collectors.toList());
                MedicineDonation medicineDonation = new MedicineDonation(items, dto.getDonorId(), dto.getDate());
                return new MedicineDonationAdapter(medicineDonation, donor, inventoryService);
            default:
                throw new IllegalArgumentException("Invalid donation type: " + type);
        }
    }
}
