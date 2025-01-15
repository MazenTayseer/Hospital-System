package com.example.hospital.services.state;

import com.example.hospital.exceptions.BadRequestException;
import com.example.hospital.models.Appointment;
import com.example.hospital.models.enums.AppointmentStatus;


public class ConfirmedState implements IAppointmentState {
    @Override
    public void nextState(Appointment appointment) {
        appointment.setStatus(AppointmentStatus.COMPLETED);
        appointment.setState(new CompletedState());
    }

    @Override
    public void cancel(Appointment appointment) {
        throw new BadRequestException("Cannot cancel appointment in Confirmed state.");
    }
    
    @Override
    public void decline(Appointment appointment) {
        throw new BadRequestException("Cannot decline appointment in Confirmed state.");
    }
}
