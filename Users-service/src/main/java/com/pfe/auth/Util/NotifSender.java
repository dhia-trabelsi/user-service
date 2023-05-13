package com.pfe.auth.Util;



import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class NotifSender {
    
    String url = "http://localhost:8084/notification";
    private RestTemplate restTemplate = new RestTemplate();



    public void sendNotif(NotifRequest notifRequest) {
    
             restTemplate.postForEntity(url, notifRequest, String.class);
    }
}