package com.exercise.webhook.services;

import com.exercise.webhook.config.TwilioConfiguration;
import com.exercise.webhook.dto.SmsRequest;
import com.exercise.webhook.interfac.SmsSender;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("twilio")
public class TwilioSmsSender implements SmsSender {

    private final static Logger LOGGER = LoggerFactory.getLogger(TwilioSmsSender.class);
    private final TwilioConfiguration twilioConfiguration;

    @Autowired
    public TwilioSmsSender(TwilioConfiguration twilioConfiguration) {
        this.twilioConfiguration = twilioConfiguration;
    }

    @Override
    public void sendSms(SmsRequest smsRequest) throws IllegalAccessException {

        if(IsPhoneNumberValid(smsRequest.getPhoneNumber())) {

            PhoneNumber to = new PhoneNumber(smsRequest.getPhoneNumber());
            PhoneNumber from = new PhoneNumber(twilioConfiguration.getTiral_number());
            String message = smsRequest.getMessage();

            MessageCreator creator = Message.creator(to, from, message);
            creator.create();
            LOGGER.info("Send sms {}"+smsRequest.getPhoneNumber());
        }else{
            throw new IllegalAccessException("Phone Number ["+smsRequest.getPhoneNumber()+"] is not a valid number!");
        }

    }

    private boolean IsPhoneNumberValid(String phoneNumber) {
        return true;
    }
}
