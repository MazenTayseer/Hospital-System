package com.example.hospital.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hospital.models.TherapyTreatment;

@Repository
public interface TherapyTreatmentRepository extends JpaRepository<TherapyTreatment, Long> {  List<TherapyTreatment> findByPatientId(Long patientId);
}
