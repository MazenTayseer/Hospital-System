package com.example.hospital.services.strategy.create_user;

import com.example.hospital.dal.NotificationServiceDAL;
import com.example.hospital.dto.UserDto;
import com.example.hospital.exceptions.BadRequestException;
import com.example.hospital.models.NotificationService;
import com.example.hospital.models.User;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class CreateUserContext {
    ICreateUser strategy;
    private final Map<String, ICreateUser> userCreationStrategies;
    private final PasswordEncoder passwordEncoder;
    private NotificationServiceDAL notificationServiceDAL;

    public CreateUserContext(
        Map<String, ICreateUser> userCreationStrategies, 
        PasswordEncoder passwordEncoder, 
        NotificationServiceDAL notificationServiceDAL
    ) {
        this.userCreationStrategies = userCreationStrategies;
        this.passwordEncoder = passwordEncoder;
        this.notificationServiceDAL = notificationServiceDAL;
    }

    public User createUser(UserDto<? extends User> request) {
        User user = request.getUser();

        String userType = user.getClass().getSimpleName().toLowerCase();
        this.setStrategy(userType);
        
        user.setPassword(passwordEncoder.encode(user.getPassword()));


        this.addNotificationServices(user, request.getNotificationServiceIds());

        return this.strategy.createUser(user);
    }

    public void setStrategy(String strategy) {
        this.strategy = userCreationStrategies.get(strategy);

        if (this.strategy == null) {
            throw new BadRequestException("Unsupported user type: " + strategy);
        }
    }

    private void addNotificationServices(User user, List<Long> notificationServiceIds) {
        if (notificationServiceIds == null) {
            notificationServiceIds = new ArrayList<>();
        }

        NotificationService emailService = notificationServiceDAL.findByName("EMAIL");
        
        if (!notificationServiceIds.contains(emailService.getId())) {
            notificationServiceIds.add(emailService.getId());
        }

        List<NotificationService> services = notificationServiceDAL.findAllById(notificationServiceIds);
        if (services.size() != notificationServiceIds.size()) {
            throw new BadRequestException("One or more NotificationService IDs are invalid.");
        }

        for (NotificationService service : services) {
            user.addNotificationService(service);
        }
    }

}
