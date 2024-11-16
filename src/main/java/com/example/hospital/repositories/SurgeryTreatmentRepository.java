package com.example.hospital.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hospital.models.SurgeryTreatment;

@Repository
public interface SurgeryTreatmentRepository extends JpaRepository<SurgeryTreatment, Long> {}
