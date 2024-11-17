package com.example.hospital.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hospital.models.MedicationTreatment;

@Repository
public interface MedicationTreatmentRepository extends JpaRepository<MedicationTreatment, Long> {
    List<MedicationTreatment> findByPatientId(Long patientId);

}
