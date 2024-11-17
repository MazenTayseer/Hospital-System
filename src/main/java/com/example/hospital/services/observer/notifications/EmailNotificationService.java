package com.example.hospital.services.observer.notifications;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.hospital.dal.UserDAL;
import com.example.hospital.models.User;

@Component
public class EmailNotificationService implements INotificationObserver {
    private final UserDAL userDAL;

    public EmailNotificationService(UserDAL userDAL) {
        this.userDAL = userDAL;
    }

    @Override
    public void send(String message) {
        List<User> users = getSubscribedUsers();

        for (User user : users) {
            System.out.println("Sending EMAIL notification to user: " + user.getEmail() + " with message: " + message);
        }
    }

    @Override
    public List<User> getSubscribedUsers() {
        return userDAL.getUsersByNotificationService("EMAIL");
    }
}
