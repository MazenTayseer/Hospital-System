package com.example.hospital.services.users.strategy;

import com.example.hospital.models.Doctor;
import com.example.hospital.models.User;
import com.example.hospital.repositories.DoctorRepository;
import org.springframework.stereotype.Component;

@Component("doctor")
public class DoctorCreationStrategy implements ICreateUser {
    private DoctorRepository doctorRepository;

    public DoctorCreationStrategy(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public User createUser(User user) {
        return doctorRepository.save((Doctor) user);
    }
}
