package com.example.hospital.services.strategy.create_user;


import com.example.hospital.dal.PatientDAL;
import com.example.hospital.dal.RoleDAL;
import com.example.hospital.models.Patient;
import com.example.hospital.models.User;
import com.example.hospital.services.decorator.roles.IRole;
import com.example.hospital.services.decorator.roles.PatientDecorator;
import com.example.hospital.services.decorator.roles.UserRole;

import org.springframework.stereotype.Component;

@Component("patient")
public class PatientCreationStrategy implements ICreateUser {
    private PatientDAL patientDAL;
    private IRole role;
    private RoleDAL roleDAL;

    public PatientCreationStrategy(PatientDAL patientDAL, IRole role, RoleDAL roleDAL) {
        this.patientDAL = patientDAL;
        this.role = role;
        this.roleDAL = roleDAL;
    }

    @Override
    public User createUser(User user) {
        this.role = new PatientDecorator(new UserRole(roleDAL), roleDAL);
        this.role.addRole(user);
        return patientDAL.save((Patient) user);
    }
}
