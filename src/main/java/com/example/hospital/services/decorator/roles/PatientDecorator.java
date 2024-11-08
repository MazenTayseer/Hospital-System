package com.example.hospital.services.decorator.roles;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.hospital.dal.RoleDAL;
import com.example.hospital.models.Role;
import com.example.hospital.models.User;

import org.springframework.stereotype.Component;

@Component
public class PatientDecorator extends RoleDecorator {
    @Autowired
    private RoleDAL roleDAL;

    public PatientDecorator(IRole role, RoleDAL roleDAL) {
        super(role);
        this.roleDAL = roleDAL;
    }

    @Override
    public void addRole(User user) {
        role.addRole(user);

        Role newRole = roleDAL.findByName("PATIENT");
        user.addRole(newRole);
    }

}
