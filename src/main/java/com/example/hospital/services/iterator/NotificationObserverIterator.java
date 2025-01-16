package com.example.hospital.services.iterator;

import com.example.hospital.services.observer.notifications.INotificationObserver;
import java.util.List;

public class NotificationObserverIterator implements Iterator<INotificationObserver> {
    private final List<INotificationObserver> observers;
    private int position = 0;

    public NotificationObserverIterator(List<INotificationObserver> observers) {
        this.observers = observers;
    }

    @Override
    public boolean hasNext() {
        return position < observers.size();
    }

    @Override
    public INotificationObserver next() {
        return observers.get(position++);
    }
}
