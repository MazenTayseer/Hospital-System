package com.example.hospital.repositories;

import com.example.hospital.models.PatientTreatment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientTreatmentRepository extends JpaRepository<PatientTreatment, Long> {

  List<PatientTreatment> findByPatientId(Long patientId);

  @Query("SELECT t FROM PatientTreatment t WHERE t.patient.id = :patientId ORDER BY t.date DESC")
  Optional<PatientTreatment> findMostRecentTreatment(@Param("patientId") Long patientId);



}
