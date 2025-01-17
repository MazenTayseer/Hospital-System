package com.example.hospital.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hospital.models.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
}

