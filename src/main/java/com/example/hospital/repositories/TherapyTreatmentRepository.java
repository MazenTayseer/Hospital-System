package com.example.hospital.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hospital.models.TherapyTreatment;

@Repository
public interface TherapyTreatmentRepository extends JpaRepository<TherapyTreatment, Long> {}
