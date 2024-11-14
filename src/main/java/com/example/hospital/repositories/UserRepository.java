package com.example.hospital.repositories;

import com.example.hospital.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    
    @Query("SELECT u FROM User u JOIN u.notificationServices ns WHERE ns.name = :serviceName")
    List<User> getUsersByNotificationService(@Param("serviceName") String serviceName);
}
