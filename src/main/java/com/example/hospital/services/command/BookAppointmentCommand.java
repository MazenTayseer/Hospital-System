package com.example.hospital.services.command;

import com.example.hospital.models.Appointment;
import com.example.hospital.services.PatientService;

public class BookAppointmentCommand implements Icommand{
    private final PatientService patientService;
    private final Appointment appointment;

    public BookAppointmentCommand(PatientService patientService, Appointment appointment) {
        this.patientService = patientService;
        this.appointment = appointment;
    }

    @Override
    public void execute() {
        patientService.bookAppointment(appointment);
    }
}
