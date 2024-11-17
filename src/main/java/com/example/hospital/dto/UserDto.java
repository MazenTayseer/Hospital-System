package com.example.hospital.dto;

import java.util.List;

public class UserDto<T> {
    private T user;
    private List<Long> notificationServiceIds;

    public UserDto() {}

    public T getUser() {
        return user;
    }

    public List<Long> getNotificationServiceIds() {
        return notificationServiceIds;
    }

    public void setUser(T user) {
        this.user = user;
    }

    public void setNotificationServiceIds(List<Long> notificationServiceIds) {
        this.notificationServiceIds = notificationServiceIds;
    }
}
