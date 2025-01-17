package com.example.hospital.dto;


public class AppointmentDto {
    private Long doctorId;

    private Long patientId;

    private String date;

    private String timeFrom;

    public AppointmentDto(Long doctorId, Long patientId, String date, String timeFrom) {
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.date = date;
        this.timeFrom = timeFrom;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public String getDate() {
        return date;
    }

    public String getTimeFrom() {
        return timeFrom;
    }
}
