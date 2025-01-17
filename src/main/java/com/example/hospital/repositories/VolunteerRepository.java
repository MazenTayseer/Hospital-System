package com.example.hospital.repositories;

import com.example.hospital.models.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {

    @Query("SELECT v FROM Volunteer v JOIN v.events e WHERE e.id = :eventId")
    List<Volunteer> findByEventId(@Param("eventId") Long eventId);
}

