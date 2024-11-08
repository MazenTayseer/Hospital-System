package com.example.hospital.services.strategy.create_user;


import com.example.hospital.models.Nurse;
import com.example.hospital.models.User;
import com.example.hospital.services.decorator.roles.IRole;
import com.example.hospital.services.decorator.roles.NurseDecorator;
import com.example.hospital.services.decorator.roles.UserRole;

import org.springframework.stereotype.Component;
import com.example.hospital.dal.NurseDAL;
import com.example.hospital.dal.RoleDAL;

@Component("nurse")
public class NurseCreationStrategy implements ICreateUser {
    private NurseDAL NurseDAL;
    private IRole role;
    private RoleDAL roleDAL;

    public NurseCreationStrategy(NurseDAL NurseDAL, IRole role, RoleDAL roleDAL) {
        this.NurseDAL = NurseDAL;
        this.role = role;
        this.roleDAL = roleDAL;
    }

    @Override
    public User createUser(User user) {
        this.role = new NurseDecorator(new UserRole(roleDAL), roleDAL);
        this.role.addRole(user);
        return NurseDAL.save((Nurse) user);
    }
}
