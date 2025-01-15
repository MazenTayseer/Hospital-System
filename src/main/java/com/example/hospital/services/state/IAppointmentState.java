package com.example.hospital.services.state;

import com.example.hospital.models.Appointment;

public interface IAppointmentState {
    void nextState(Appointment appointment);
    void cancel(Appointment appointment);
    void decline(Appointment appointment);
}
