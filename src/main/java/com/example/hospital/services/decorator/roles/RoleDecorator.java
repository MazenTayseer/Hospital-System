package com.example.hospital.services.decorator.roles;

import com.example.hospital.models.User;

public abstract class RoleDecorator implements IRole {
    protected IRole role;

    public RoleDecorator(IRole role) {
        this.role = role;
    }

    @Override
    public void addRole(User user) {
        role.addRole(user);
    }
}
