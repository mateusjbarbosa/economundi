package com.backend.economundi.consumer;

import java.io.IOException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class ApiNewsConsumer {

    public void getNews() throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "https://newsapi.org/v2/top-headlines?country=br&caregory=business&apiKey=e8f44036f3254db8bcd6e31c3c9ca1b0";
        ResponseEntity<String> response = restTemplate.getForEntity(fooResourceUrl, String.class);
        
        System.out.println(response);
    }
}
