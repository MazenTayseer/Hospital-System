package com.example.hospital.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.hospital.dal.NotificationServiceDAL;
import com.example.hospital.dal.RoleDAL;
import com.example.hospital.models.NotificationService;
import com.example.hospital.models.Role;

@Service
public class StaticsService {
    private final RoleDAL roleDAL;
    private final NotificationServiceDAL notificationServiceDAL;

    public StaticsService(RoleDAL roleDAL, NotificationServiceDAL notificationServiceDAL) {
        this.roleDAL = roleDAL;
        this.notificationServiceDAL = notificationServiceDAL;
    }

    public List<Role> getAllRoles() {
        return roleDAL.getRoles();
    }

    public List<NotificationService> getNotificationServices() {
        return notificationServiceDAL.findAll();
    }
}
