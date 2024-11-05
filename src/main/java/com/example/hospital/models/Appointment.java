package com.example.hospital.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import com.example.hospital.ResponseMessages;

@Entity
@Table(
    name = "appointments", 
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"doctor_id", "date", "timeFrom", "timeTo"})
    }
)
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Date is required")
    @Future(message = "The appointment date must be in the future")
    private LocalDate date;

    @NotNull(message = "Start time is required")
    private LocalTime timeFrom;

    @NotNull(message = "End time is required")
    private LocalTime timeTo;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    @NotNull(message = "Doctor is required")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    @NotNull(message = "Patient is required")
    private Patient patient;

    public Appointment() {}

    public Long getId() { return this.id; }
    public LocalDate getDate() { return this.date; }
    public LocalTime getTimeFrom() { return this.timeFrom; }
    public LocalTime getTimeTo() { return this.timeTo; }
    public Doctor getDoctor() { return this.doctor; }
    public Patient getPatient() { return this.patient; }

    public void setDoctor(Doctor doctor) { this.doctor = doctor; }
    public void setPatient(Patient patient) { this.patient = patient; }

    public void setTimeFrom(LocalTime timeFrom) {
        this.timeFrom = timeFrom;
        this.timeTo = timeFrom.plus(1, ChronoUnit.HOURS);
        validateTime();
    }

    private void validateTime() {
        if (this.timeFrom != null && this.timeTo != null) {
            long hoursBetween = ChronoUnit.HOURS.between(this.timeFrom, this.timeTo);
            if (hoursBetween != 1 || this.timeFrom.getMinute() != 0 || this.timeTo.getMinute() != 0) {
                throw new IllegalArgumentException(ResponseMessages.INVALID_APPOINTMENT_TIME_ERR);
            }
        }
    }
}
