package com.example.hospital;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.hospital.dal.AppointmentDAL;
import com.example.hospital.dal.DoctorDAL;
import com.example.hospital.dal.PatientDAL;
import com.example.hospital.dal.ReviewDAL;
import com.example.hospital.services.PatientService;
import com.example.hospital.services.singleton.PatientServiceSingleton;

public class PatientServiceSingletonTest {
    @Autowired
    private DoctorDAL doctorDAL;

    @Autowired
    private PatientDAL patientDAL;

    @Autowired
    private AppointmentDAL appointmentDAL;

    @Autowired
    private ReviewDAL reviewDAL;

    @Test
    public void testSingletonInstance() {
        PatientService instance1 = PatientServiceSingleton.getInstance(doctorDAL, patientDAL, appointmentDAL, reviewDAL);
        PatientService instance2 = PatientServiceSingleton.getInstance(doctorDAL, patientDAL, appointmentDAL, reviewDAL);

        assertSame(instance1, instance2, "Both instances should be the same");
    }
}