package com.example.hospital.services.state;

import com.example.hospital.models.Appointment;
import com.example.hospital.models.enums.AppointmentStatus;


public class RequestedState implements IAppointmentState {
    @Override
    public void nextState(Appointment appointment) {
        appointment.setStatus(AppointmentStatus.CONFIRMED);
        appointment.setState(new ConfirmedState());
    }

    @Override
    public void cancel(Appointment appointment) {
        appointment.setStatus(AppointmentStatus.CANCELLED);
        appointment.setState(new CancelledState());
    }

    @Override
    public void decline(Appointment appointment) {
        appointment.setStatus(AppointmentStatus.DECLINED);
        appointment.setState(new DeclinedState());
    }
}
