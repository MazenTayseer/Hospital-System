package com.example.hospital.services.users;

import com.example.hospital.models.User;
import org.springframework.stereotype.Service;

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
