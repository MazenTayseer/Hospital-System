package com.example.hospital.services.state;

import com.example.hospital.exceptions.BadRequestException;
import com.example.hospital.models.Appointment;

public class DeclinedState implements IAppointmentState {
    @Override
    public void nextState(Appointment appointment) {
        throw new BadRequestException("Cannot move to next state from Declined state.");
    }

    @Override
    public void cancel(Appointment appointment) {
        throw new BadRequestException("Cannot cancel appointment in Declined state.");
    }

    @Override
    public void decline(Appointment appointment) {
        throw new BadRequestException("Cannot decline appointment in Declined state.");
    }
    
}
