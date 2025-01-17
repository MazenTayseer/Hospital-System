package com.example.hospital.services;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import io.github.cdimascio.dotenv.Dotenv;

import org.springframework.stereotype.Service;

@Service
public class SmsService {

    private String accountSid;

    private String authToken;

    private String fromPhoneNumber;

    public SmsService() {
        Dotenv dotenv = Dotenv.load();
        this.accountSid = dotenv.get("TWILIO_ACCOUNT_SID");
        this.authToken = dotenv.get("TWILIO_AUTH_TOKEN");
        this.fromPhoneNumber = dotenv.get("TWILIO_PHONE_NUMBER");
    }

    public void sendSms(String to, String message) {
        Twilio.init(accountSid, authToken);
        Message.creator(
                new PhoneNumber(to),
                new PhoneNumber(fromPhoneNumber),
                message
        ).create();
    }
}
