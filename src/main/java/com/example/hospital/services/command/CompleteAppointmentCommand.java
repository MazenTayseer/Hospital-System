package com.example.hospital.services.command;

import com.example.hospital.services.DoctorService;

public class CompleteAppointmentCommand implements Icommand {
    
    private final DoctorService doctorService;
    private final Long appointmentId;

    public CompleteAppointmentCommand(DoctorService doctorService, Long appointmentId) {
        this.doctorService = doctorService;
        this.appointmentId = appointmentId;
    }

    @Override
    public void execute() {
        doctorService.changeAppointmentStatus(appointmentId);
    }
}
