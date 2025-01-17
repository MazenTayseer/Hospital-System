// File: src/main/java/com/example/hospital/services/ShiftService.java
package com.example.hospital.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hospital.dal.ShiftDAL;
import com.example.hospital.dal.NurseDAL;
import com.example.hospital.models.Shift;
import com.example.hospital.models.Nurse;

import java.util.List;

@Service
public class ShiftService {

    private final ShiftDAL shiftDAL;
    private final NurseDAL nurseDAL;

    @Autowired
    public ShiftService(ShiftDAL shiftDAL, NurseDAL nurseDAL) {
        this.shiftDAL = shiftDAL;
        this.nurseDAL = nurseDAL;
    }

    public List<Shift> getAllShifts() {
        return shiftDAL.findAll();
    }

    public Shift getShiftById(Long id) {
        return shiftDAL.findById(id);
    }

    public Shift createShift(Shift shift) {
        Nurse nurse = nurseDAL.findById(shift.getNurse().getId());
        if (nurse != null) {
            shift.setNurse(nurse);
            return shiftDAL.save(shift);
        }
        return null;
    }

    public Shift updateShift(Long id, Shift updatedShift) {
        Shift shift = shiftDAL.findById(id);
        if (shift != null) {
            shift.setStartTime(updatedShift.getStartTime());
            shift.setEndTime(updatedShift.getEndTime());
            Nurse nurse = nurseDAL.findById(updatedShift.getNurse().getId());
            if (nurse != null) {
                shift.setNurse(nurse);
            }
            return shiftDAL.save(shift);
        }
        return null;
    }

    public void deleteShift(Long id) {
        Shift shift = shiftDAL.findById(id);
        if (shift != null) {
            shiftDAL.delete(shift);
        }
    }

    public List<Shift> getShiftsByNurseId(Long nurseId) {
        return shiftDAL.findAll().stream()
                .filter(shift -> shift.getNurse() != null && shift.getNurse().getId().equals(nurseId))
                .toList();
    }
}
