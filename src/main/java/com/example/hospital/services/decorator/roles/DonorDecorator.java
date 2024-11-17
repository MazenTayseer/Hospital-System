package com.example.hospital.services.decorator.roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.hospital.dal.RoleDAL;
import com.example.hospital.models.Role;
import com.example.hospital.models.User;
@Component
public class DonorDecorator extends RoleDecorator {
    @Autowired
    private RoleDAL roleDAL;

    public DonorDecorator(IRole role, RoleDAL roleDAL) {
        super(role);
        this.roleDAL = roleDAL;
    }

    @Override
    public void addRole(User user) {
        role.addRole(user);

        Role newRole = roleDAL.findByName("DONOR");
        user.addRole(newRole);
    }

}