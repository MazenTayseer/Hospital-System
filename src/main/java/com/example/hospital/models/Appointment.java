package com.example.hospital.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import com.example.hospital.ResponseMessages;
import com.example.hospital.models.enums.AppointmentStatus;

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

    @Column(nullable = false)
    @NotNull(message = "The appointment date is required")
    @Future(message = "The appointment date must be in the future")
    private LocalDate date;

    @Column(nullable = false)
    @NotNull(message = "The start time of the appointment is required")
    private LocalTime timeFrom;

    @Column(nullable = false)
    @NotNull(message = "The end time of the appointment is required")
    private LocalTime timeTo;

    @Column(nullable = false)
    @NotNull(message = "The status of the appointment is required")
    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    @NotNull(message = "The doctor for the appointment is required")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    @NotNull(message = "The patient for the appointment is required")
    private Patient patient;

    public Appointment() {
        this.status = AppointmentStatus.SCHEDULED;
    }

    public Long getId() { return this.id; }
    public LocalDate getDate() { return this.date; }
    public LocalTime getTimeFrom() { return this.timeFrom; }
    public LocalTime getTimeTo() { return this.timeTo; }
    public AppointmentStatus getStatus() { return this.status; }
    public Doctor getDoctor() { return this.doctor; }
    public Patient getPatient() { return this.patient; }

    public void setDoctor(Doctor doctor) { this.doctor = doctor; }
    public void setPatient(Patient patient) { this.patient = patient; }

    public void setTimeFrom(LocalTime timeFrom) {
        this.timeFrom = timeFrom;
        this.timeTo = timeFrom.plus(1, ChronoUnit.HOURS);
        validateTime();
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
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
