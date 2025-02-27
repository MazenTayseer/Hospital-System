package com.example.hospital.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import com.example.hospital.ResponseMessages;
import com.example.hospital.exceptions.BadRequestException;
import com.example.hospital.models.enums.AppointmentStatus;
import com.example.hospital.services.state.CancelledState;
import com.example.hospital.services.state.CompletedState;
import com.example.hospital.services.state.ConfirmedState;
import com.example.hospital.services.state.IAppointmentState;
import com.example.hospital.services.state.RequestedState;

@Entity
@Table(name = "appointments", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "doctor_id", "date", "timeFrom", "timeTo" })
})
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

    @Transient
    private IAppointmentState state;

    public Appointment() {
        this.status = AppointmentStatus.REQUESTED;
        initializeState();
    }

    public Appointment(
            LocalDate date,
            LocalTime timeFrom,
            Doctor doctor,
            Patient patient) {
        this.date = date;
        setTimeFrom(timeFrom);
        this.doctor = doctor;
        this.patient = patient;
        this.status = AppointmentStatus.REQUESTED;
        initializeState();
    }

    public Long getId() {
        return this.id;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public LocalTime getTimeFrom() {
        return this.timeFrom;
    }

    public LocalTime getTimeTo() {
        return this.timeTo;
    }

    public AppointmentStatus getStatus() {
        return this.status;
    }

    public Doctor getDoctor() {
        return this.doctor;
    }

    public Patient getPatient() {
        return this.patient;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setTimeFrom(LocalTime timeFrom) {
        this.timeFrom = timeFrom;
        this.timeTo = timeFrom.plus(1, ChronoUnit.HOURS);
        validateTime();
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }

    public void setState(IAppointmentState state) {
        this.state = state;
    }

    public void nextState() {
        this.state.nextState(this);
    }

    public void cancel() {
        this.state.cancel(this);
    }

    public void decline() {
        this.state.decline(this);
    }
    
    private void validateTime() {
        if (this.timeFrom != null && this.timeTo != null) {
            long hoursBetween = ChronoUnit.HOURS.between(this.timeFrom, this.timeTo);
            if (hoursBetween != 1 || this.timeFrom.getMinute() != 0 || this.timeTo.getMinute() != 0) {
                throw new BadRequestException(ResponseMessages.INVALID_APPOINTMENT_TIME_ERR);
            }
        }
    }

    @PostLoad
    private void initializeState() {
        switch (this.status) {
            case REQUESTED:
                this.state = new RequestedState();
                break;
            case CONFIRMED:
                this.state = new ConfirmedState();
                break;
            case COMPLETED:
                this.state = new CompletedState();
                break;
            case CANCELLED:
                this.state = new CancelledState();
                break;
            default:
                throw new IllegalStateException("Unknown appointment status");
        }
    }
}
