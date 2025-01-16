package com.example.hospital.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hospital.models.Prescription;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
  Optional<Prescription> findByPatientIdAndDoctorIdAndMedicationName(Long patientId, Long doctorId, String medicationName);

}
