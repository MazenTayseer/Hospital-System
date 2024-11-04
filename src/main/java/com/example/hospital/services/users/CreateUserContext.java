package com.example.hospital.services.users;

import com.example.hospital.models.*;
import com.example.hospital.services.users.strategy.*;
import org.springframework.stereotype.Component;

@Component
public class CreateUserContext {
    private DoctorCreationStrategy doctorCreationStrategy;
    private NurseCreationStrategy nurseCreationStrategy;
    private PatientCreationStrategy patientCreationStrategy;

    public CreateUserContext(
        DoctorCreationStrategy doctorCreationStrategy, 
        NurseCreationStrategy nurseCreationStrategy,
        PatientCreationStrategy patientCreationStrategy
    ) {
        this.doctorCreationStrategy = doctorCreationStrategy;
        this.nurseCreationStrategy = nurseCreationStrategy;
        this.patientCreationStrategy = patientCreationStrategy;
    }

    public User createUser(User user) {
        ICreateUser strategy = setStrategy(user);
        return strategy.createUser(user);
    }

    private ICreateUser setStrategy(User user) {
        if (user instanceof Doctor) {
            return doctorCreationStrategy;
        } else if (user instanceof Nurse) {
            return nurseCreationStrategy;
        } else if (user instanceof Patient) {
            return patientCreationStrategy;
        }

        throw new IllegalArgumentException("Unsupported user type");
    }
}
