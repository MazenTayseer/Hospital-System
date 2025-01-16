package com.example.hospital.services.iterator;

import java.util.List;
import com.example.hospital.models.User;

public class UserIterator implements Iterator<User> {
    private final List<User> users;
    private int position;

    public UserIterator(List<User> users) {
        this.users = users;
        this.position = 0;
    }

    @Override
    public boolean hasNext() {
        return position < users.size();
    }

    @Override
    public User next() {
        if (!hasNext()) {
            throw new IllegalStateException("No more elements");
        }
        return users.get(position++);
    }
}
