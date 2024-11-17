package com.example.hospital.services.strategy.create_user;

import org.springframework.stereotype.Component;

import com.example.hospital.dal.DonorDAL;
import com.example.hospital.dal.RoleDAL;
import com.example.hospital.models.Donor;
import com.example.hospital.models.User;
import com.example.hospital.services.decorator.roles.DonorDecorator;
import com.example.hospital.services.decorator.roles.IRole;
import com.example.hospital.services.decorator.roles.UserRole;

@Component("donor")
public class DonorCreationStrategy implements ICreateUser {
    private DonorDAL donorDAL;
    private IRole role;
    private RoleDAL roleDAL;

    public DonorCreationStrategy(DonorDAL donorDAL, IRole role, RoleDAL roleDAL) {
        this.donorDAL = donorDAL;
        this.role = role;
        this.roleDAL = roleDAL;
    }

    @Override
    public User createUser(User user) {
        this.role = new DonorDecorator(new UserRole(roleDAL), roleDAL);
        this.role.addRole(user);
        return donorDAL.save((Donor) user);
    }
}
