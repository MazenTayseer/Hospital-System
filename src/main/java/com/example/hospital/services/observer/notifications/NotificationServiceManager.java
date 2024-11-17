package com.example.hospital.services.observer.notifications;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class NotificationServiceManager implements INotificationSubject {
    List<INotificationObserver> observers;

    public NotificationServiceManager() {
        this.observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(INotificationObserver observer) {
        this.observers.add(observer);
    }

    @Override
    public void removeObserver(INotificationObserver observer) {
        this.observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (INotificationObserver observer : this.observers) {
            observer.send(message);
        }
    }
    
}
