package com.example.hospital.repositories;

import com.example.hospital.models.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {
    List<Volunteer> findByEventId(Long eventId);
}
