package com.example.hospital.services.iterator;

import java.util.List;
import com.example.hospital.services.observer.notifications.INotificationObserver;

public class NotificationObserverIterator implements Iterator<INotificationObserver> {
    private final List<INotificationObserver> observers;
    private int position;

    public NotificationObserverIterator(List<INotificationObserver> observers) {
        this.observers = observers;
        this.position = 0;
    }

    @Override
    public boolean hasNext() {
        return position < observers.size();
    }

    @Override
    public INotificationObserver next() {
        if (!hasNext()) {
            throw new IllegalStateException("No more elements");
        }
        return observers.get(position++);
    }
}
