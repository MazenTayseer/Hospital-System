package com.example.hospital.services.users.strategy;

import com.example.hospital.models.Nurse;
import com.example.hospital.models.User;
import org.springframework.stereotype.Component;
import com.example.hospital.dal.NurseDAL;

@Component("nurse")
public class NurseCreationStrategy implements ICreateUser {
    private NurseDAL NurseDAL;

    public NurseCreationStrategy(NurseDAL NurseDAL) {
        this.NurseDAL = NurseDAL;
    }

    @Override
    public User createUser(User user) {
        return NurseDAL.save((Nurse) user);
    }
}
