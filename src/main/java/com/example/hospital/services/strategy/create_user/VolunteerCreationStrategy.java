package com.example.hospital.services.strategy.create_user;


import com.example.hospital.dal.VolunteerDAL;
import com.example.hospital.dal.RoleDAL;
import com.example.hospital.models.Volunteer;
import com.example.hospital.models.User;
import com.example.hospital.services.decorator.roles.IRole;
import com.example.hospital.services.decorator.roles.VolunteerDecorator;
import com.example.hospital.services.decorator.roles.UserRole;

import org.springframework.stereotype.Component;

@Component("volunteer")
public class VolunteerCreationStrategy implements ICreateUser {
    private VolunteerDAL volunteerDAL;
    private IRole role;
    private RoleDAL roleDAL;

    public VolunteerCreationStrategy(VolunteerDAL volunteerDAL, IRole role, RoleDAL roleDAL) {
        this.volunteerDAL = volunteerDAL;
        this.role = role;
        this.roleDAL = roleDAL;
    }

    @Override
    public User createUser(User user) {
        this.role = new VolunteerDecorator(new UserRole(roleDAL), roleDAL);
        this.role.addRole(user);
        return volunteerDAL.save((Volunteer) user);
    }
}
