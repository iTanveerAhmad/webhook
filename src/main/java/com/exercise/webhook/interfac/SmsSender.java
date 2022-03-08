package com.exercise.webhook.interfac;

import com.exercise.webhook.dto.SmsRequest;

public interface SmsSender {

    void sendSms(SmsRequest smsRequest) throws IllegalAccessException;

}
