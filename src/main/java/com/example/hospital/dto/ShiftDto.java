// File: src/main/java/com/example/hospital/dto/ShiftDto.java
package com.example.hospital.dto;

import java.time.LocalDateTime;

public class ShiftDto {
    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Long nurseId;
    private String nurseName;

    public ShiftDto() {
    }

    public ShiftDto(Long id, LocalDateTime startTime, LocalDateTime endTime, Long nurseId, String nurseName) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.nurseId = nurseId;
        this.nurseName = nurseName;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Long getNurseId() {
        return nurseId;
    }

    public void setNurseId(Long nurseId) {
        this.nurseId = nurseId;
    }

    public String getNurseName() {
        return nurseName;
    }

    public void setNurseName(String nurseName) {
        this.nurseName = nurseName;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
