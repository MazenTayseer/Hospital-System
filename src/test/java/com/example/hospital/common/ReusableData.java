package com.example.hospital.common;

import com.example.hospital.models.Doctor;
import com.example.hospital.models.Nurse;
import com.example.hospital.models.Patient;
import com.example.hospital.models.enums.Gender;
import com.example.hospital.models.enums.Speciality;

public class ReusableData {

    public static Doctor createDoctor() {
        return new Doctor(
                "doctor",
                "doctor",
                "doctor@eng.asu.edu.eg",
                "password",
                "+201279936001",
                21,
                Gender.MALE,
                Speciality.SURGEON);
    }

    public static Patient createPatient() {
        return new Patient(
                "patient",
                "patient",
                "patient@eng.asu.edu.eg",
                "password",
                "+201279936003",
                21,
                Gender.MALE,
                createNurse(),
                "Allergic to penicillin.");
    }

    public static Nurse createNurse() {
        return new Nurse(
                "nurse",
                "nurse",
                "nurse@eng.asu.edu.eg",
                "password",
                "+201279936002",
                21,
                Gender.MALE);
    }
}
