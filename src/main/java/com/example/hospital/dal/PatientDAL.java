package com.example.hospital.dal;

import com.example.hospital.ResponseMessages;
import com.example.hospital.exceptions.BadRequestException;
import com.example.hospital.models.Patient;
import org.springframework.stereotype.Component;
import com.example.hospital.repositories.PatientRepository;


@Component
public class PatientDAL {

    private final PatientRepository patientRepository;

    public PatientDAL(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient findById(Long id) {
        return patientRepository.findById(id).orElseThrow(
            () -> new BadRequestException(ResponseMessages.record_not_found("Patient"))
        );
    }

    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }

    public void delete(Patient patient) {
        patientRepository.delete(patient);
    }
}