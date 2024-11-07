package com.example.hospital.configs;

import com.example.hospital.models.Manager;
import com.example.hospital.models.enums.Gender;
import com.example.hospital.models.enums.Role;
import com.example.hospital.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @SuppressWarnings("unused")
    @Bean
    public CommandLineRunner initializeData(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByEmail("maziiin360@gmail.com").isEmpty()) {
                Manager defaultManager = new Manager(
                    "Default",
                    "Manager",
                    "maziiin360@gmail.com",
                    passwordEncoder.encode("test1234"),
                    "1234567890",
                    40,
                    Gender.MALE
                );
                defaultManager.setRole(Role.MANAGER);

                userRepository.save(defaultManager);
            }
        };
    }
}
