package com.example.hospital.services.decorator.roles;

import com.example.hospital.dal.RoleDAL;
import com.example.hospital.models.Role;
import com.example.hospital.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class UserRole implements IRole {
    @Autowired
    private RoleDAL roleDAL;

    public UserRole(RoleDAL roleDAL) {
        this.roleDAL = roleDAL;
    }

    @Override
    public void addRole(User user) {
        Role role = roleDAL.findByName("USER");
        user.addRole(role);
    }
}
