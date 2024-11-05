package com.example.hospital.services.users;

import org.springframework.stereotype.Service;

import com.example.hospital.models.User;

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
