package com.example.hospital.services.singleton;

import com.example.hospital.dal.DoctorDAL;
import com.example.hospital.dal.PatientDAL;
import com.example.hospital.dal.AppointmentDAL;
import com.example.hospital.dal.ReviewDAL;
import com.example.hospital.services.PatientService;

public class PatientServiceSingleton {
    private static PatientService instance;

    private PatientServiceSingleton() {}

    public static synchronized PatientService getInstance(
            DoctorDAL doctorDAL,
            PatientDAL patientDAL,
            AppointmentDAL appointmentDAL,
            ReviewDAL reviewDAL) {
        if (instance == null) {
            instance = new PatientService(doctorDAL, patientDAL, appointmentDAL, reviewDAL);
        }
        return instance;
    }
}
