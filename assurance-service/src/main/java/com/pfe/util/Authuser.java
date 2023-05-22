package com.pfe.util;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;


public class Authuser {

    RestTemplate restTemplate = new RestTemplate();
    String url = "http://localhost:8082/api/user/authid";

    public Integer getAuthId() {
        return restTemplate.getForObject(url, Integer.class);
    }


    @PostMapping
    public Integer getId(@RequestBody Integer id) {
        return id;
    }

}