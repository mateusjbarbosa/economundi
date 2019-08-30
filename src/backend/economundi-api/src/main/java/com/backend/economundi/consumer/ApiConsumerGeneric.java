package com.backend.economundi.consumer;

import com.google.gson.Gson;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class ApiConsumerGeneric {
    
    public <T extends Object> T getData(String url, Class<T> classOfT) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity
                                            (url, String.class);
        Gson g = new Gson();
        
        return g.fromJson(response.getBody(), classOfT);
    }
}
