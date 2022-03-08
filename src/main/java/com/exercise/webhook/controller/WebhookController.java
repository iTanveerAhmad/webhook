package com.exercise.webhook.controller;

import com.exercise.webhook.dto.SmsRequest;
import com.twilio.twiml.MessagingResponse;
import com.twilio.twiml.messaging.Body;
import com.twilio.twiml.messaging.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import com.exercise.webhook.services.Service;

import javax.validation.Valid;

@RestController
public class WebhookController {

    private final static Logger LOGGER = LoggerFactory.getLogger(WebhookController.class);

    private final Service service;

    public WebhookController(Service service) {
        this.service = service;
    }

    @PostMapping("/api/v1/sms")
    @ResponseBody
    public String sendSms(@Valid @RequestBody SmsRequest smsRequest){
        try {
            LOGGER.info("Criteria matched for triggering the event :"+smsRequest.getMessage().contains("YES"));
            if(smsRequest.getMessage().contains("YES")) {
                service.sendSMS(smsRequest);

            Body body = new Body
                    .Builder("Thank you for contacting us!")
                    .build();
            Message sms = new Message
                    .Builder()
                    .body(body)
                    .build();
            MessagingResponse twiml = new MessagingResponse
                    .Builder()
                    .message(sms)
                    .build();
            return twiml.toXml();
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping(value = "/api/v1/received-sms", produces = ("text/xml"))
    @ResponseBody
    public String replySms(){


        Body.Builder builder = new Body.Builder("Thank you for contacting us!");
        Message message = new Message.Builder()
                            .body(builder.build())
                            .build();
        MessagingResponse twiml = new MessagingResponse.Builder()
                                            .message(message)
                                            .build();

       return twiml.toXml();
    }


//    private AtomicInteger callerNumber = new AtomicInteger();
//
//    @GetMapping(path="/call", produces="application/xml")
//    @ResponseBody
//    public String respondToPhoneCall() {
//
//        VoiceResponse.Builder voiceBuilder = new VoiceResponse.Builder();
//
//        Say greeting = new Say.Builder("Hello caller number " + callerNumber.incrementAndGet()).build();
//        Play music = new Play.Builder("https://static.gilliard.lol/Might_As_Well_Be_Spring.mp3").build();
//
//        return voiceBuilder.say(greeting).play(music).build().toXml();
//    }
}