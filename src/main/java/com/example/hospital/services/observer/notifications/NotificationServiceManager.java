package com.example.hospital.services.observer.notifications;

import java.util.ArrayList;

import org.springframework.stereotype.Component;
import com.example.hospital.services.iterator.Iterator;

import com.example.hospital.services.iterator.*;

@Component
public class NotificationServiceManager implements INotificationSubject {
    private final NotificationCollection notificationCollection;

    public NotificationServiceManager() {
        this.notificationCollection = new NotificationCollection(new ArrayList<>());
    }

    @Override
    public void registerObserver(INotificationObserver observer) {
        notificationCollection.getObservers().add(observer);
    }

    @Override
    public void removeObserver(INotificationObserver observer) {
        notificationCollection.getObservers().remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        Iterator<INotificationObserver> iterator = notificationCollection.createIterator();

        while (iterator.hasNext()) {
            INotificationObserver observer = iterator.next();
            observer.send(message);
        }
    }
}
