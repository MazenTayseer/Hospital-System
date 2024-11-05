package com.example.hospital.services.users;

import com.example.hospital.models.User;
import com.example.hospital.services.users.strategy.ICreateUser;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CreateUserContext {
    ICreateUser strategy;
    private final Map<String, ICreateUser> userCreationStrategies;

    public CreateUserContext(Map<String, ICreateUser> userCreationStrategies) {
        this.userCreationStrategies = userCreationStrategies;
    }

    public User createUser(User user) {
        String userType = user.getClass().getSimpleName().toLowerCase();
        this.setStrategy(userType);
        return this.strategy.createUser(user);
    }

    public void setStrategy(String strategy) {
        this.strategy = userCreationStrategies.get(strategy);

        if (this.strategy == null) {
            throw new IllegalArgumentException("Unsupported user type: " + strategy);
        }
    }
}
