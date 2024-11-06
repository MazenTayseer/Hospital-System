package com.example.hospital.services.users.strategy;

import com.example.hospital.dal.PatientDAL;
import com.example.hospital.models.Patient;
import com.example.hospital.models.User;
import org.springframework.stereotype.Component;

@Component("patient")
public class PatientCreationStrategy implements ICreateUser {
    private PatientDAL patientDAL;

    public PatientCreationStrategy(PatientDAL patientDAL) {
        this.patientDAL = patientDAL;
    }

    @Override
    public User createUser(User user) {
        return patientDAL.save((Patient) user);
    }
}
