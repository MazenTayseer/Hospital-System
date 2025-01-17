package com.example.hospital.services.payment;

public class PaymentRequest {
    private float amount;

    public PaymentRequest(float amount) {
        this.amount = amount;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
