package com.example.hospital.services.observer.notifications;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.hospital.dal.UserDAL;
import com.example.hospital.models.User;
import com.example.hospital.services.iterator.UserIterator;
import com.example.hospital.services.iterator.Iterator;

@Component
public class SmsNotificationService implements INotificationObserver {
    private final UserDAL userDAL;

    public SmsNotificationService(UserDAL userDAL) {
        this.userDAL = userDAL;
    }

    @Override
    public void send(String message) {
        List<User> users = getSubscribedUsers();
        Iterator<User> userIterator = new UserIterator(users);

        while (userIterator.hasNext()) {
            User user = userIterator.next();
            System.out.println("Sending SMS notification to user: " + user.getEmail() + " with message: " + message);
        }
    }

    @Override
    public List<User> getSubscribedUsers() {
        return userDAL.getUsersByNotificationService("SMS");
    }
}
