// File: src/main/java/com/example/hospital/dal/ShiftDAL.java
package com.example.hospital.dal;

import com.example.hospital.models.Shift;
import com.example.hospital.repositories.ShiftRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShiftDAL {

    private final ShiftRepository shiftRepository;

    public ShiftDAL(ShiftRepository shiftRepository) {
        this.shiftRepository = shiftRepository;
    }

    public Shift save(Shift shift) {
        return shiftRepository.save(shift);
    }

    public List<Shift> findAll() {
        return shiftRepository.findAll();
    }

    public Shift findById(Long id) {
        return shiftRepository.findById(id).orElse(null);
    }

    public void delete(Shift shift) {
        shiftRepository.delete(shift);
    }

    public void deleteAll() {
        shiftRepository.deleteAll();
    }
}
