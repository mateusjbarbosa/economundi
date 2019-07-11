package com.backend.economundi.consumer;

import com.backend.economundi.entity.ResultNews;
import com.google.gson.Gson;
import java.io.IOException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class ApiNewsConsumer {

    public void getNews() throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "https://newsapi.org/v2/top-headlines?country=br&caregory=business&apiKey=e8f44036f3254db8bcd6e31c3c9ca1b0";
        ResponseEntity<String> response = restTemplate.getForEntity(fooResourceUrl, String.class);
        Gson g = new Gson();
        
        System.out.println(response.getBody());
        ResultNews result = g.fromJson(response.getBody(), ResultNews.class);
        
        System.out.println(result);
    }
}
