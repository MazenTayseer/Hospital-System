package com.example.hospital.services;

import org.springframework.stereotype.Service;

import com.example.hospital.models.User;
import com.example.hospital.services.strategy.create_user.CreateUserContext;

@Service
public class ManagerService {
    private final CreateUserContext createUserContext;

    public ManagerService(CreateUserContext createUserContext) {
        this.createUserContext = createUserContext;
    }

    public User createUser(User user) {
        return createUserContext.createUser(user);
    }
}
