package com.example.hospital.dto;


public class AppointmentDto {
    private Long doctorId;

    private Long patientId;

    private String date;

    private String timeFrom;

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
