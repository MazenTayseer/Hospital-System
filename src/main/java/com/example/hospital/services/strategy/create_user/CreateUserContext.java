package com.example.hospital.services.strategy.create_user;

import com.example.hospital.exceptions.BadRequestException;
import com.example.hospital.models.User;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CreateUserContext {
    ICreateUser strategy;
    private final Map<String, ICreateUser> userCreationStrategies;
    private final PasswordEncoder passwordEncoder;

    public CreateUserContext(Map<String, ICreateUser> userCreationStrategies, PasswordEncoder passwordEncoder) {
        this.userCreationStrategies = userCreationStrategies;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(User user) {
        String userType = user.getClass().getSimpleName().toLowerCase();
        this.setStrategy(userType);
        
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return this.strategy.createUser(user);
    }

    public void setStrategy(String strategy) {
        this.strategy = userCreationStrategies.get(strategy);

        if (this.strategy == null) {
            throw new BadRequestException("Unsupported user type: " + strategy);
        }
    }
}
