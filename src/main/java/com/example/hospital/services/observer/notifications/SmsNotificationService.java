package com.example.hospital.services.observer.notifications;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.hospital.dal.UserDAL;
import com.example.hospital.models.User;
import com.example.hospital.services.iterator.UserIterator;
import com.example.hospital.services.SmsService;
import com.example.hospital.services.iterator.Iterator;

@Component
public class SmsNotificationService implements INotificationObserver {
    private final UserDAL userDAL;
    private final SmsService smsService;

    public SmsNotificationService(UserDAL userDAL, SmsService smsService) {
        this.userDAL = userDAL;
        this.smsService = smsService;
    }

    @Override
    public void send(String message, String subject) {
        List<User> users = getSubscribedUsers();
        Iterator<User> userIterator = new UserIterator(users);

        while (userIterator.hasNext()) {
            User user = userIterator.next();
            smsService.sendSms(user.getPhone(), message);
        }
    }

    @Override
    public List<User> getSubscribedUsers() {
        return userDAL.getUsersByNotificationService("SMS");
    }
}
