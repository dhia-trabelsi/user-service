package com.pfe.Util;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class HistorySender {
    String url = "http://localhost:8084/history";
    private RestTemplate restTemplate = new RestTemplate();



    public void sendHistory(HistoryRequest historyRequest) {
    
             restTemplate.postForEntity(url, historyRequest, String.class);
    }
}