package com.example.hospital.services.iterator;

import java.util.List;
import com.example.hospital.services.observer.notifications.INotificationObserver;

public class NotificationCollection implements IterableCollection<INotificationObserver> {
    private final List<INotificationObserver> observers;

    public NotificationCollection(List<INotificationObserver> observers) {
        this.observers = observers;
    }

    @Override
    public Iterator<INotificationObserver> createIterator() {
        return new NotificationObserverIterator(observers);
    }

    // New method to return the observers list
    public List<INotificationObserver> getObservers() {
        return observers;
    }
}
