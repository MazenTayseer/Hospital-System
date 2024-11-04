package com.example.hospital.services.users.strategy;

import com.example.hospital.models.Nurse;
import com.example.hospital.models.User;
import com.example.hospital.repositories.NurseRepository;
import org.springframework.stereotype.Component;

@Component
public class PatientCreattio implements ICreateUser {
    private NurseRepository nurseRepository;

    public PatientCreattio(NurseRepository nurseRepository) {
        this.nurseRepository = nurseRepository;
    }

    @Override
    public User createUser(User user) {
        return nurseRepository.save((Nurse) user);
    }
}
