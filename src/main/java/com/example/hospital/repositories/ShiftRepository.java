// File: src/main/java/com/example/hospital/repositories/ShiftRepository.java
package com.example.hospital.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.hospital.models.Shift;

@Repository
public interface ShiftRepository extends JpaRepository<Shift, Long> {

}
