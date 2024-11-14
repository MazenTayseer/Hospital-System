package com.example.hospital.repositories;

import com.example.hospital.models.PatientTreatment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface PatientTreatmentRepository extends JpaRepository<PatientTreatment, Long> {

    // Find all treatments for a specific patient by patient ID
    List<PatientTreatment> findByPatientId(Long patientId);

    // Find all treatments for a specific patient by patient ID and treatment type
    List<PatientTreatment> findByPatientIdAndTreatmentType(Long patientId, String treatmentType);

    // Find a specific treatment by patient ID and treatment type
    Optional<PatientTreatment> findByPatientIdAndTreatmentTypeAndId(Long patientId, String treatmentType, Long treatmentId);

    // Find all treatments for a specific patient (using a JPQL query)
    @Query("SELECT pt FROM PatientTreatment pt WHERE pt.patient.id = :patientId")
    List<PatientTreatment> findTreatmentsByPatientId(Long patientId);

    // Find treatments by treatment type for all patients (using JPQL)
    @Query("SELECT pt FROM PatientTreatment pt WHERE pt.treatmentType = :treatmentType")
    List<PatientTreatment> findTreatmentsByType(String treatmentType);

    // Find all treatments applied after a specific date (using JPQL)
    @Query("SELECT pt FROM PatientTreatment pt WHERE pt.treatmentDate > :date")
    List<PatientTreatment> findTreatmentsAfterDate(String date);  // This will now work with the treatmentDate field
}
