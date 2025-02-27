package com.example.hospital.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hospital.models.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
}
