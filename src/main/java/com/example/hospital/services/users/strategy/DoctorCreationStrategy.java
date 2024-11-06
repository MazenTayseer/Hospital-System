package com.example.hospital.services.users.strategy;

import com.example.hospital.dal.DoctorDAL;
import com.example.hospital.models.Doctor;
import com.example.hospital.models.User;
import org.springframework.stereotype.Component;

@Component("doctor")
public class DoctorCreationStrategy implements ICreateUser {
    private DoctorDAL doctorDAL;

    public DoctorCreationStrategy(DoctorDAL doctorDAL) {
        this.doctorDAL = doctorDAL;
    }

    @Override
    public User createUser(User user) {
        return doctorDAL.save((Doctor) user);
    }
}
