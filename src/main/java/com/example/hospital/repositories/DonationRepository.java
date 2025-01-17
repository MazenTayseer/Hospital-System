package com.example.hospital.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.hospital.models.BaseDonation;

public interface DonationRepository extends JpaRepository<BaseDonation, Long> {
  @Query("SELECT d FROM BaseDonation d LEFT JOIN FETCH d.donor")
    List<BaseDonation> findAllDonations();
}
