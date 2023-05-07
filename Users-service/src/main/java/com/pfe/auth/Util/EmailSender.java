package com.pfe.auth.Util;

import java.io.UnsupportedEncodingException;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import jakarta.mail.MessagingException;

@Configuration
public class EmailSender {


    String url = "http://localhost:8084/email";
    private RestTemplate restTemplate = new RestTemplate();


    public void sendEmail(EmailRequest emailRequest) {
        

        restTemplate.postForEntity(url, emailRequest, String.class);
    }

    
}
