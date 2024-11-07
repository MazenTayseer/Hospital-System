package com.example.hospital.dal;

import com.example.hospital.models.Nurse;
import org.springframework.stereotype.Component;
import com.example.hospital.repositories.NurseRepository;


@Component
public class NurseDAL {

    private final NurseRepository nurseRepository;

    public NurseDAL(NurseRepository nurseRepository) {
        this.nurseRepository = nurseRepository;
    }

    public Nurse save(Nurse patient) {
        return nurseRepository.save(patient);
    }

    public void deleteAll() {
        nurseRepository.deleteAll();
    }

}
