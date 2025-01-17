package com.example.hospital.dal;

import com.example.hospital.ResponseMessages;
import com.example.hospital.exceptions.BadRequestException;
import com.example.hospital.models.NotificationService;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.hospital.repositories.NotificationServiceRepository;

@Component
public class NotificationServiceDAL {

    private final NotificationServiceRepository notificationServiceRepository;

    public NotificationServiceDAL(NotificationServiceRepository notificationServiceRepository) {
        this.notificationServiceRepository = notificationServiceRepository;
    }

    public NotificationService save(NotificationService notificationService) {
        return notificationServiceRepository.save(notificationService);
    }

    public List<NotificationService> findAllById(List<Long> ids) {
        return notificationServiceRepository.findAllById(ids);
    }

    public List<NotificationService> findAll() {
        return notificationServiceRepository.findAll();
    }

    public NotificationService findByName(String name) {
        return notificationServiceRepository.findByName(name).orElseThrow(
            () -> new BadRequestException(ResponseMessages.record_not_found("Role"))
        );
    }
}
