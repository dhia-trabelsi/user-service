package com.pfe.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Authuser {

    RestTemplate restTemplate = new RestTemplate();
    String url = "http://localhost:8082/api/user/broker";

    public Integer getAuthId() {
        return restTemplate.getForObject(url, Integer.class);
    }
}
