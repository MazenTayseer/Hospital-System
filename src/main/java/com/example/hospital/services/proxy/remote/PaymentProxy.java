package com.example.hospital.services.proxy.remote;

import java.rmi.Naming;
import java.rmi.RemoteException;

import org.springframework.stereotype.Component;

@Component
public class PaymentProxy implements IPaymentService {
    @Override
    public String processPayment(Long donorId, double amount) throws RemoteException {
        try {
            IPaymentService paymentService = (IPaymentService) Naming.lookup("rmi://127.0.0.1/PaymentService");
            System.out.println("Proxy forwarding request to RMI Payment Service...");
            return paymentService.processPayment(donorId, amount);
        } catch (Exception e) {
            e.printStackTrace();
            return "Payment failed";
        }
    }
}
