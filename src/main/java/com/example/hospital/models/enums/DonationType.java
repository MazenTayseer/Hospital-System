package com.example.hospital.models.enums;

public enum DonationType {
    MONEY("money"),
    MEDICINE("medicine");

    private String type;

    DonationType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
