package com.example.hospital.services.users.strategy;

import com.example.hospital.models.Patient;
import com.example.hospital.models.User;
import com.example.hospital.repositories.PatientRepository;
import org.springframework.stereotype.Component;

@Component
public class PatientCreationStrategy implements ICreateUser {
    private PatientRepository patientRepository;

    public PatientCreationStrategy(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public User createUser(User user) {
        return patientRepository.save((Patient) user);
    }
}
