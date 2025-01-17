package com.example.hospital.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class MedicineDonation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    @CollectionTable(name = "medicine_items", joinColumns = @JoinColumn(name = "medicine_donation_id"))
    private List<MedicineItem> medicineItems;

    @Column(nullable = false)
    private Long donorId;

    @Column(nullable = false)
    private LocalDate donationDate;

    public MedicineDonation() {
    }

    public MedicineDonation(List<MedicineItem> medicineItems, Long donorId, LocalDate donationDate) {
        this.medicineItems = medicineItems;
        this.donorId = donorId;
        this.donationDate = donationDate;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public List<MedicineItem> getMedicineItems() {
        return medicineItems;
    }

    public void setMedicineItems(List<MedicineItem> medicineItems) {
        this.medicineItems = medicineItems;
    }

    public Long getDonorId() {
        return donorId;
    }

    public void setDonorId(Long donorId) {
        this.donorId = donorId;
    }

    public LocalDate getDonationDate() {
        return donationDate;
    }

    public void setDonationDate(LocalDate donationDate) {
        this.donationDate = donationDate;
    }
}
