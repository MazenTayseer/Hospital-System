package com.example.hospital.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hospital.models.Bill;

public interface BillRepository extends JpaRepository<Bill, Long> {}
