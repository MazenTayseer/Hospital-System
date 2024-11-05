package com.example.hospital.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hospital.models.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
}
