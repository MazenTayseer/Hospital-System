package com.example.hospital.configs;

import com.example.hospital.dal.RoleDAL;
import com.example.hospital.models.Manager;
import com.example.hospital.models.Role;
import com.example.hospital.models.enums.Gender;
import com.example.hospital.repositories.RoleRepository;
import com.example.hospital.repositories.UserRepository;
import com.example.hospital.services.decorator.roles.DoctorDecorator;
import com.example.hospital.services.decorator.roles.IRole;
import com.example.hospital.services.decorator.roles.ManagerDecorator;
import com.example.hospital.services.decorator.roles.NurseDecorator;
import com.example.hospital.services.decorator.roles.PatientDecorator;
import com.example.hospital.services.decorator.roles.UserRole;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @SuppressWarnings("unused")
    @Bean
    public CommandLineRunner initializeData(
        UserRepository userRepository, 
        PasswordEncoder passwordEncoder, 
        RoleRepository roleRepository,
        RoleDAL roleDAL
    ) {
        return args -> {
            String[] roles = {"USER", "NURSE", "PATIENT", "MANAGER", "DOCTOR","VOLUNTEER"};
            for (String roleName : roles) {
                if (roleRepository.findByName(roleName).isEmpty()) {
                    roleRepository.save(new Role(roleName));
                }
            }

            if (userRepository.findByEmail("maziiin360@gmail.com").isEmpty()) {
                Manager defaultManager = new Manager(
                    "Admin",
                    "Admin",
                    "maziiin360@gmail.com",
                    passwordEncoder.encode("test1234"),
                    "1234567890",
                    40,
                    Gender.MALE
                );
                IRole role = new ManagerDecorator(
                    new NurseDecorator(
                        new PatientDecorator(
                            new DoctorDecorator(
                                new UserRole(roleDAL), 
                            roleDAL), 
                        roleDAL),
                     roleDAL),
                roleDAL
                );
                role.addRole(defaultManager);

                userRepository.save(defaultManager);
            }
        };
    }
}

