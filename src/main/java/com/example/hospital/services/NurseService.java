package com.example.hospital.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hospital.dal.NurseDAL;
import com.example.hospital.dal.PatientDAL;
import com.example.hospital.models.Nurse;
import com.example.hospital.models.Patient;

import java.util.List;

@Service
public class NurseService {

    private final NurseDAL nurseDAL;
    private final PatientDAL patientDAL;

    @Autowired
    public NurseService(NurseDAL nurseDAL, PatientDAL patientDAL) {
        this.nurseDAL = nurseDAL;
        this.patientDAL = patientDAL;
    }

    public List<Nurse> getAllNurses() {
        return nurseDAL.findAll();
    }

    public Nurse getNurseById(Long id) {
        return nurseDAL.findById(id);
    }

    public Patient assignPatientToNurse(Long nurseId, Long patientId) {
        Nurse nurse = nurseDAL.findById(nurseId);
        Patient patient = patientDAL.findById(patientId);
        if (nurse != null && patient != null) {
            patient.setAssignedNurse(nurse);
            patientDAL.save(patient);
            return patient;
        }
        return null;
    }

    public List<Patient> getPatientsOfNurse(Long nurseId) {
        return patientDAL.findAll().stream()
                .filter(p -> p.getAssignedNurse() != null && p.getAssignedNurse().getId().equals(nurseId))
                .toList();
    }
}
