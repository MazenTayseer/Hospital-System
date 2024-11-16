package com.example.hospital.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hospital.models.MedicationTreatment;

@Repository
public interface MedicationTreatmentRepository extends JpaRepository<MedicationTreatment, Long> {}
