package com.example.hospital.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hospital.models.Nurse;

@Repository
public interface NurseRepository extends JpaRepository<Nurse, Long> {
}
