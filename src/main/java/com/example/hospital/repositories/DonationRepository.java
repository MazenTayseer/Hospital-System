package com.example.hospital.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hospital.models.BaseDonation;

public interface DonationRepository extends JpaRepository<BaseDonation, Long> {
}