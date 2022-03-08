package com.exercise.webhook.services;

import com.exercise.webhook.dto.SmsRequest;
import com.exercise.webhook.interfac.SmsSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@org.springframework.stereotype.Service
public class Service {

    private final SmsSender smsSender;

    @Autowired
    public Service(@Qualifier("twilio") TwilioSmsSender twilioSmsSender) {
        this.smsSender = twilioSmsSender;
    }

    public void sendSMS(SmsRequest smsRequest) throws IllegalAccessException {
        smsSender.sendSms(smsRequest);
    }

}
