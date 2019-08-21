package com.backend.economundi.consumer;

import com.backend.economundi.entity.ResultNews;
import java.io.IOException;

public class ApiNewsConsumer {
    
    private final String URL =
        "https://newsapi.org/v2/top-headlines?country=br&category="
            + "business&apiKey=e8f44036f3254db8bcd6e31c3c9ca1b0";

    public void getNews() throws IOException { 
        ApiConsumerGeneric generic = new ApiConsumerGeneric();
        ResultNews result = generic.getData(URL, ResultNews.class);
        
        System.out.println(result.toString());
    }
}
