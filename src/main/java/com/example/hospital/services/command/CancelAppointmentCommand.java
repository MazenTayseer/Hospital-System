package com.example.hospital.services.command;

import com.example.hospital.services.PatientService;

public class CancelAppointmentCommand implements Icommand {
    private final PatientService patientService;
    private final Long appointmentId;

    public CancelAppointmentCommand(PatientService patientService, Long appointmentId) {
        this.patientService = patientService;
        this.appointmentId = appointmentId;
    }

    @Override
    public void execute() {
        patientService.cancelAppointment(appointmentId);
    }
}
