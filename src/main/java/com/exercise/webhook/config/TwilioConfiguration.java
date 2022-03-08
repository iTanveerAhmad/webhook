package com.exercise.webhook.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("twilio")
public class TwilioConfiguration {

   private String account_sid;
   private String auth_tokem;
   private String tiral_number;

    public TwilioConfiguration() {
    }

    public String getAccount_sid() {
        return account_sid;
    }

    public void setAccount_sid(String account_sid) {
        this.account_sid = account_sid;
    }

    public String getAuth_tokem() {
        return auth_tokem;
    }

    public void setAuth_tokem(String auth_tokem) {
        this.auth_tokem = auth_tokem;
    }

    public String getTiral_number() {
        return tiral_number;
    }

    public void setTiral_number(String tiral_number) {
        this.tiral_number = tiral_number;
    }
}
