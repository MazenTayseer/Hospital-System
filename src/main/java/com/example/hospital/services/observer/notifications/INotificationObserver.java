package com.example.hospital.services.observer.notifications;

import java.util.List;

import com.example.hospital.models.User;

public interface INotificationObserver {
    void send(String message, String subject);
    List<User> getSubscribedUsers();
}
