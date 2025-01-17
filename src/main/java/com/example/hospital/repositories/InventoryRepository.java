package com.example.hospital.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hospital.models.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
  Inventory findByMedicationName(String medicationName);

}
