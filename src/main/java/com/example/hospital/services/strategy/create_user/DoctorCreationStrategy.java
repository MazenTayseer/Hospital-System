package com.example.hospital.services.strategy.create_user;


import com.example.hospital.dal.DoctorDAL;
import com.example.hospital.dal.RoleDAL;
import com.example.hospital.models.Doctor;
import com.example.hospital.models.User;
import com.example.hospital.services.decorator.roles.DoctorDecorator;
import com.example.hospital.services.decorator.roles.IRole;
import com.example.hospital.services.decorator.roles.UserRole;

import org.springframework.stereotype.Component;

@Component("doctor")
public class DoctorCreationStrategy implements ICreateUser {
    private DoctorDAL doctorDAL;
    private IRole role;
    private RoleDAL roleDAL;

    public DoctorCreationStrategy(DoctorDAL doctorDAL, IRole role, RoleDAL roleDAL) {
        this.doctorDAL = doctorDAL;
        this.role = role;
        this.roleDAL = roleDAL;
    }

    @Override
    public User createUser(User user) {
        this.role = new DoctorDecorator(new UserRole(roleDAL), roleDAL);
        this.role.addRole(user);
        return doctorDAL.save((Doctor) user);
    }
}
