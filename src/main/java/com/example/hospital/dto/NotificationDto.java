package com.example.hospital.dto;

public class NotificationDto {
    private String message;
    private String subject;

    public NotificationDto(String message, String subject) {
        this.message = message;
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public String getSubject() {
        return subject;
    }
}
