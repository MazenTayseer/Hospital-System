package com.example.hospital.services.iterator;

import java.util.List;
import com.example.hospital.models.User;

public class UserCollection implements IterableCollection<User> {
    private final List<User> users;

    public UserCollection(List<User> users) {
        this.users = users;
    }

    @Override
    public Iterator<User> createIterator() {
        return new UserIterator(users);
    }
}
