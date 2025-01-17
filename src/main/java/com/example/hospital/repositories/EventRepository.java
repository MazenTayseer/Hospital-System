package com.example.hospital.repositories;

import com.example.hospital.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    // Custom query methods can be added here, e.g., find events by date or location
}
