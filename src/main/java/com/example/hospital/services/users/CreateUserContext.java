package com.example.hospital.services.users;

import com.example.hospital.models.Doctor;
import com.example.hospital.models.Nurse;
import com.example.hospital.models.User;
import com.example.hospital.services.users.strategy.DoctorCreationStrategy;
import com.example.hospital.services.users.strategy.ICreateUser;
import com.example.hospital.services.users.strategy.NurseCreationStrategy;
import org.springframework.stereotype.Component;

@Component
public class CreateUserContext {
    private final DoctorCreationStrategy doctorCreationStrategy;
    private final NurseCreationStrategy nurseCreationStrategy;

    public CreateUserContext(DoctorCreationStrategy doctorCreationStrategy, NurseCreationStrategy nurseCreationStrategy) {
        this.doctorCreationStrategy = doctorCreationStrategy;
        this.nurseCreationStrategy = nurseCreationStrategy;
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
        } else {
            throw new IllegalArgumentException("Unsupported user type");
        }
    }
}
