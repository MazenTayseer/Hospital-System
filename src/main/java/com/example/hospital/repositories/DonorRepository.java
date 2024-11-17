package com.example.hospital.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.hospital.models.Donor;

@Repository
public interface DonorRepository extends JpaRepository<Donor, Long> {
    // Additional query methods specific to donations can go here
}
