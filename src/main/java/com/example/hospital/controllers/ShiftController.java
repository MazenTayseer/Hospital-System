// File: src/main/java/com/example/hospital/controllers/ShiftController.java
package com.example.hospital.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.hospital.models.Shift;
import com.example.hospital.services.ShiftService;
import com.example.hospital.services.NurseService;
import com.example.hospital.dto.ShiftDto;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/shifts")
public class ShiftController {

    private final ShiftService shiftService;
    private final NurseService nurseService;

    @Autowired
    public ShiftController(ShiftService shiftService, NurseService nurseService) {
        this.shiftService = shiftService;
        this.nurseService = nurseService;
    }

    /**
     * Get all shifts
     */
    @GetMapping
    public ResponseEntity<List<ShiftDto>> getAllShifts() {
        List<Shift> shifts = shiftService.getAllShifts();
        List<ShiftDto> shiftDtos = shifts.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(shiftDtos);
    }

    /**
     * Get shift by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<ShiftDto> getShiftById(@PathVariable Long id) {
        Shift shift = shiftService.getShiftById(id);
        if (shift != null) {
            return ResponseEntity.ok(convertToDto(shift));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Create a new shift
     */
    @PostMapping
    public ResponseEntity<ShiftDto> createShift(@RequestBody ShiftDto shiftDto) {
        Shift shift = new Shift(
                shiftDto.getStartTime(),
                shiftDto.getEndTime(),
                nurseService.getNurseById(shiftDto.getNurseId()));
        Shift createdShift = shiftService.createShift(shift);
        if (createdShift != null) {
            return ResponseEntity.ok(convertToDto(createdShift));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Update a shift
     */
    @PutMapping("/{id}")
    public ResponseEntity<ShiftDto> updateShift(@PathVariable Long id, @RequestBody ShiftDto shiftDto) {
        Shift updatedShift = new Shift(
                shiftDto.getStartTime(),
                shiftDto.getEndTime(),
                nurseService.getNurseById(shiftDto.getNurseId()));
        Shift shift = shiftService.updateShift(id, updatedShift);
        if (shift != null) {
            return ResponseEntity.ok(convertToDto(shift));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Delete a shift
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShift(@PathVariable Long id) {
        shiftService.deleteShift(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Get shifts by nurse ID
     */
    @GetMapping("/nurse/{nurseId}")
    public ResponseEntity<List<ShiftDto>> getShiftsByNurseId(@PathVariable Long nurseId) {
        List<Shift> shifts = shiftService.getShiftsByNurseId(nurseId);
        List<ShiftDto> shiftDtos = shifts.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(shiftDtos);
    }

    // Helper method to convert entity to DTO
    private ShiftDto convertToDto(Shift shift) {
        ShiftDto dto = new ShiftDto();
        dto.setId(shift.getId());
        dto.setStartTime(shift.getStartTime());
        dto.setEndTime(shift.getEndTime());
        if (shift.getNurse() != null) {
            dto.setNurseId(shift.getNurse().getId());
            dto.setNurseName(shift.getNurse().getFirstName() + " " + shift.getNurse().getLastName());
        }
        return dto;
    }
}
