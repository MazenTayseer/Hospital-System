package com.example.hospital.configs;

import com.example.hospital.dal.RoleDAL;
import com.example.hospital.models.Manager;
import com.example.hospital.models.NotificationService;
import com.example.hospital.models.Role;
import com.example.hospital.models.enums.Gender;
import com.example.hospital.repositories.NotificationServiceRepository;
import com.example.hospital.repositories.RoleRepository;
import com.example.hospital.repositories.UserRepository;
import com.example.hospital.services.decorator.roles.DoctorDecorator;
import com.example.hospital.services.decorator.roles.DonorDecorator;
import com.example.hospital.services.decorator.roles.IRole;
import com.example.hospital.services.decorator.roles.ManagerDecorator;
import com.example.hospital.services.decorator.roles.NurseDecorator;
import com.example.hospital.services.decorator.roles.PatientDecorator;
import com.example.hospital.services.decorator.roles.UserRole;
import com.example.hospital.services.observer.notifications.EmailNotificationService;
import com.example.hospital.services.observer.notifications.NotificationServiceManager;
import com.example.hospital.services.observer.notifications.SmsNotificationService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Configuration
public class DataInitializer {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final RoleDAL roleDAL;
    private final NotificationServiceRepository notificationServiceRepository;
    private final EmailNotificationService emailService;
    private final SmsNotificationService smsService;
    private final NotificationServiceManager notificationManager;

    public DataInitializer(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           RoleRepository roleRepository,
                           RoleDAL roleDAL,
                           NotificationServiceRepository notificationServiceRepository,
                           EmailNotificationService emailService,
                           SmsNotificationService smsService,
                           NotificationServiceManager notificationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.roleDAL = roleDAL;
        this.notificationServiceRepository = notificationServiceRepository;
        this.emailService = emailService;
        this.smsService = smsService;
        this.notificationManager = notificationManager;
    }

    @SuppressWarnings("unused")
    @Bean
    public CommandLineRunner initializeData() {
        return args -> {
            initializeRoles();
            initializeNotificationServices();
            createDefaultManager();
        };
    }

    private void initializeRoles() {
        String[] roles = {"USER", "NURSE", "PATIENT", "MANAGER", "DOCTOR", "DONOR", "VOLUNTEER"};
        for (String roleName : roles) {
            roleRepository.findByName(roleName)
                .orElseGet(() -> roleRepository.save(new Role(roleName)));
        }
    }

    @SuppressWarnings("unused")
    private void initializeNotificationServices() {
        String[] notificationServices = {"SMS", "EMAIL"};
        for (String serviceName : notificationServices) {
            notificationServiceRepository.findByName(serviceName)
                .orElseGet(() -> notificationServiceRepository.save(new NotificationService(serviceName)));
        }

        Optional<NotificationService> emailServiceEntity = notificationServiceRepository.findByName("EMAIL");
        Optional<NotificationService> smsServiceEntity = notificationServiceRepository.findByName("SMS");

        emailServiceEntity.ifPresent(service -> {
            notificationManager.registerObserver(emailService);
            System.out.println("Email service registered");
        });

        smsServiceEntity.ifPresent(service -> {
            notificationManager.registerObserver(smsService);
            System.out.println("SMS service registered");
        });
    }

    private void createDefaultManager() {
        userRepository.findByEmail("mazen@asu.com").orElseGet(() -> {
            Manager defaultManager = new Manager(
                "Admin",
                "Admin",
                "mazen@asu.com",
                passwordEncoder.encode("test1234"),
                "1234567890",
                40,
                Gender.MALE
            );

            IRole roleDecorator = new ManagerDecorator(
                new NurseDecorator(
                    new PatientDecorator(
                        new DoctorDecorator(
                            new DonorDecorator(new UserRole(roleDAL), roleDAL),
                        roleDAL),
                    roleDAL),
                 roleDAL),
            roleDAL
            );

            roleDecorator.addRole(defaultManager);
            return userRepository.save(defaultManager);
        });
    }
}
