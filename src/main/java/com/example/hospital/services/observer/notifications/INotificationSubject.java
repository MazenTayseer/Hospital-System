package com.example.hospital.services.observer.notifications;

public interface INotificationSubject {
    void registerObserver(INotificationObserver observer);
    void removeObserver(INotificationObserver observer);
    void notifyObservers(String message, String subject);
}
