package com.example.hospital;

public class ResponseMessages {
    public static String INVALID_APPOINTMENT_TIME_ERR = "Appointment times must be exactly one hour apart and set on the hour.";
    public static String APPOINTMENT_CANCELLED = "Appointment cancelled successfully";
    public static String CANNOT_REVIEW_DOCTOR = "You can only review a doctor with whom you've had at least one appointment.";
    public static String CANNOT_CANCEL_COMPLETED_APPOINTMENT = "Cannot cancel a completed appointment";
    
    public static String record_not_found(String record) {
        return record + " not found";
    }
}
