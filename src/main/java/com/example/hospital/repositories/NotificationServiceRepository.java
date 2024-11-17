package com.example.hospital.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hospital.models.NotificationService;

@Repository
public interface NotificationServiceRepository extends JpaRepository<NotificationService, Long> {
    Optional<NotificationService> findByName(String name);
}
