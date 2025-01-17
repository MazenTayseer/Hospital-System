package com.example.hospital.services.observer.notifications;

import java.util.List;

import org.springframework.stereotype.Component;
import com.example.hospital.dal.UserDAL;
import com.example.hospital.models.User;
import com.example.hospital.services.iterator.UserIterator;
import com.example.hospital.services.iterator.Iterator;
import com.example.hospital.services.EmailSenderService;

@Component
public class EmailNotificationService implements INotificationObserver {

    private final UserDAL userDAL;
    private final EmailSenderService emailSenderService;

    public EmailNotificationService(UserDAL userDAL, EmailSenderService emailSenderService) {
        this.userDAL = userDAL;
        this.emailSenderService = emailSenderService;
    }

    @Override
    public void send(String message, String subject) {
        List<User> users = getSubscribedUsers();
        Iterator<User> userIterator = new UserIterator(users);

        while (userIterator.hasNext()) {
            User user = userIterator.next();
            emailSenderService.sendEmail(
                user.getEmail(),
                subject,
                message
            );
            System.out.println("Email sent to: " + user.getEmail());
        }
    }

    @Override
    public List<User> getSubscribedUsers() {
        return userDAL.getUsersByNotificationService("EMAIL");
    }
}
