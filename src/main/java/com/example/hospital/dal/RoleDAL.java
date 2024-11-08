package com.example.hospital.dal;


import org.springframework.stereotype.Component;

import com.example.hospital.ResponseMessages;
import com.example.hospital.exceptions.BadRequestException;
import com.example.hospital.models.Role;
import com.example.hospital.repositories.RoleRepository;

@Component
public class RoleDAL {

    private final RoleRepository roleRepository;

    public RoleDAL(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role findByName(String name) {
        return roleRepository.findByName(name).orElseThrow(
            () -> new BadRequestException(ResponseMessages.record_not_found("Role"))
        );
    }
}
